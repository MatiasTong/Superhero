/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.dao.HeroDaoImpl.HeroMapper;
import com.sms.superherosightings.dao.SuperpowerDaoImpl.SuperpowerMapper;
import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Superpower;
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
    /*Annotated as transactional because all the steps must pass for the method to execute successfully
    to ensure the Last_Insert_Id is that of the hero was just added to our database*/
    @Transactional
    public Hero create(Hero model) {
        final String INSERT_HERO = "INSERT INTO Hero(Name, Description, Superpower, Type) VALUES (?,?,?,?)";
        jdbc.update(INSERT_HERO, model.getName(), model.getDescription(), model.getSuperpower(), model.getType());
        int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);
        model.setHeroId(newId);
        return model;
    }

    @Override
    public List<Hero> readAll() {
        final String SELECT_ALL_HEROES = "SELECT * FROM Hero";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        associateHeroSuperpower(heroes);
        return heroes;
    }

    @Override
    public Hero readById(int id) {
        // A try catch is needed in case there is a null pointer exception from the queryForObject method 
        //if the heroId doesn't exist
        try {
            final String SELECT_HERO = "SELECT * FROM Hero WHERE HeroId = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO, new HeroMapper(), id);
            Superpower superpower = getSuperpowerForHero(hero);
            hero.setSuperpower(superpower);
            return hero;
            
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Hero model) {
        final String UPDATE_HERO = "UPDATE Hero SET Name= ?,Description= ?,Superpower= ?, Type= ? WHERE HeroId = ?;";
        jdbc.update(UPDATE_HERO, model.getName(), model.getDescription(), model.getSuperpower(), model.getType(), model.getHeroId());
    }

    @Override
    @Transactional
    public void delete(int id) {
        //To delete the rows in the Hero table matchting the heroID, we need to first delete all rows 
        //where the hero id is used as a foreign key, removing all the constraints
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE HeroId = ?";
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE HeroId = ?";
        final String DELETE_HERO = "DELETE FROM Hero WHERE HeroId = ?";
  
        jdbc.update(DELETE_SIGHTING, id);
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        jdbc.update(DELETE_HERO, id);
    }
    
      private Superpower getSuperpowerForHero(Hero model) {
        try {
            //see comments for getHeroForSighting()
            final String SELECT_LOCATION = "SELECT s.* FROM Superhero s JOIN Hero h ON s.SuperpowerId = h.SuperpowerId WHERE h.HeroId = ?";
            Superpower superpower  = jdbc.queryForObject(SELECT_LOCATION, new SuperpowerMapper(), model.getHeroId());
            return superpower;
        } catch (DataAccessException e) {
            return null;
        }
    }
      
       private void associateHeroSuperpower(List<Hero> heroes) {
        for (Hero hero : heroes) {
            Superpower superpower = getSuperpowerForHero(hero);
            hero.setSuperpower(superpower);
        }
    }



    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int arg1) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("HeroId"));
            hero.setName(rs.getString("Name"));
            hero.setDescription(rs.getString("Description"));
            hero.setType(rs.getString("Type"));
            return hero;
        }

    }

}
