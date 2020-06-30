/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

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
public class OrganizationDaoImpl implements Dao<Organization> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Organization create(Organization model) {
        final String sql = "INSERT INTO Organization(OrganizationId, Name, "
                + "Description,LocationId,Email,Type) VALUES (?,?,?,?,?,?)";
        jdbc.update(sql, model.getName(),
                model.getDescription(), model.getLocation().getLocationId(),
                model.getEmail(), model.getType());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        model.setOrganizationId(id);
        
        insertHeroOrganization(model);

        return model;
    }

    /**
     * Returns the results of query method call w| query String and OrgMapper as
     * parameters
     *
     * @return List of organizations
     */
    @Override
    public List<Organization> readAll() {
        final String SELECT_ALL_ORGS = "SELECT * FROM Organization";
        List<Organization> orgs = jdbc.query(SELECT_ALL_ORGS, new OrgMapper());
        //helper method get location for the org - for each loop to take in a list
        return orgs;
    }

    /**
     * Try-Catch method used since QueryForObject method will throw an exception
     * if no object is returned from the query.
     *
     * @param id OrganizationId
     * @return null in the catch to let us know that we couldn't retrieve the
     * object.
     */
    @Override
    public Organization readById(int id) {
        try {
            final String SELECT_ORG_BY_ID = "SELECT * FROM Organization WHERE OrganizationId = ?";
            return jdbc.queryForObject(SELECT_ORG_BY_ID, new OrgMapper(), id);
            //helper method here
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public void update(Organization model) {
        //double check locationid
        final String UPDATE_ORG = "UPDATE Organization SET Name = ?,"
                + " Description = ?, LocationId = ?, Email = ?, Type = ? WHERE OrganizationId =?";
        jdbc.update(UPDATE_ORG, model.getName(), model.getDescription(),model.getLocation().getLocationId(), model.getEmail(), model.getType());

        final String DELETE_HERO_ORGANIZATION = "DELETE From HeroOrganization WHERE OrganizationId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, model.getOrganizationId());
        insertHeroOrganization(model);
    }

    @Override
    public void delete(int id) {
        //double check this method
        final String DELETE_HERO_ORG = "DELETE FROM HeroOrganization WHERE OrganizationId = ?";
        final String DELETE_ORG = "DELETE FROM Organization WHERE OrganizationId = ?";
        jdbc.update(DELETE_HERO_ORG, id);
        jdbc.update(DELETE_ORG, id);
    }

    private List<Organization> getOrganizationsForHero(Hero hero) {
        final String SELECT_ORG_FOR_HERO = "SELECT o.* FROM Organization o "
                + "JOIN HeroOrganization ho ON o.OrganizationId "
                + "= ho.OrganizationId WHERE ho.HeroId = ?";
        List<Organization> organizations = jdbc.query(SELECT_ORG_FOR_HERO,
                new OrgMapper(), hero.getHeroId());
        return organizations;
    }

    private void insertHeroOrganization(Organization model) {
        List<Hero> heroes = model.getHeroes();
        for (Hero hero : heroes) {
            final String INSERT_HERO_ORGANIZATION = "INSERT INTO HeroOrganization(HeroId, OrganizationId) VALUES (?,?);";
            jdbc.update(INSERT_HERO_ORGANIZATION, hero.getHeroId(), model.getOrganizationId());
        }
    }

//    private List<Hero> getHerosForOrganization(int organizationId) {
//        final String SELECT_HEROES_FOR_ORG = "SELECT "
//    }
//    private void associateHeroes(List<Organization> organizations) {
//        for(Organization org : organizations) {
//            org.setHeroes(Heroes);
//        }
//    }

    /*Rowmapper(Interface) used to implement in a mapper class for the specified
    object we want to process. Job: Take one row of the ResultSet and return
    an object(Organization) built from that row.*/
    public static final class OrgMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("OrganizationId"));
            org.setName(rs.getString("Name"));
            org.setDescription(rs.getString("Description"));
            org.setEmail(rs.getString("Email"));
            org.setType(rs.getString("Type"));
            return org;
        }
    }

}
