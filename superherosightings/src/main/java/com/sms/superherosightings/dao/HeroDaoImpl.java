/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.dao.HeroDaoImpl.HeroMapper;
import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HeroDaoImpl implements Dao<Hero> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Hero create(Hero model) {
        final String INSERT_HERO = "INSERT INTO Hero(HeroId, `Name`,`Description`,Specialty,`Type) VALUES (?,?,?,?,?);";
        jdbc.update(INSERT_HERO, model.getHeroId(), model.getName(), model.getDescription(), model.getSuperpower(), model.getType());
        int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);
        model.setHeroId(newId);
        insertHeroOrganization(model);
        return model;
    }

    private void insertHeroOrganization(Hero model) {
        List<Organization> organizations = model.getOrganization();
        for (Organization organization : organizations) {
            final String INSERT_HERO_ORGANIZATION = "INSERT INTO HeroOrganization(HeroId, OrganizationId) VALUES (?,?);";
            jdbc.update(INSERT_HERO_ORGANIZATION, model.getHeroId(), organization.getOrganizationId());
        }

    }

    @Override
    public List<Hero> readAll() {
        final String SELECT_ALL_HEROES = "SELECT * FROM Hero";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        associateOrganizations(heroes);
        return heroes;
    }

    @Override
    public Hero readById(int id) {
        try {
            final String SELECT_HERO = "SELECT * FROM Hero WHERE HeroId = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO, new HeroMapper(), id);
            hero.setOrganization(getOrganizationsByHero(id));
            return hero;
            
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Hero model) {
        final String UPDATE_HERO = "UPDATE Hero SET `Name`= ?,`Description`= ?,Specialty`= ?, Type= ? WHERE HeroId = ?;";
        jdbc.update(UPDATE_HERO, model.getName(), model.getDescription(), model.getSuperpower(), model.getType(), model.getHeroId());
        
        final String DELETE_HERO_ORGANIZATION = "DELETE From HeroOrganization WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, model.getHeroId());
        insertHeroOrganization(model);
    }

    @Override
    @Transactional
    public void delete(int id) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE HeroId = ?";
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE HeroId = ?";
        final String DELETE_HERO = "DELETE FROM Hero WHERE HeroId = ?";
        
        jdbc.update(DELETE_SIGHTING, id);
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        jdbc.update(DELETE_HERO, id);
    }

    private List<Organization> getOrganizationsByHero(int heroId) {
        final String sql = "SELECT o.* FROM Organization o JOIN HeroOrganization ho ON o.OrganizationId "
                + "= ho.OrganizationId WHERE ho.HeroId = ?";
        List<Organization> organizations = jdbc.query(sql, new OrganizationMapper(), heroId);
        return organizations;
    }

    private void associateOrganizations(List<Hero> heroes) {
        for (Hero hero : heroes) {
            List<Organization> organizations = getOrganizationsByHero(hero.getHeroId());
            hero.setOrganization(organizations);
        }
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int arg1) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("HeroId"));
            hero.setName(rs.getString("`Name`"));
            hero.setDescription(rs.getString("`Description`"));
            hero.setSuperpower(rs.getString("Specialty"));
            hero.setType(rs.getString("`Type`"));
            return hero;
        }

    }

}
