/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.dao.HeroDaoImpl.HeroMapper;
import com.sms.superherosightings.dao.LocationDaoImpl.LocationMapper;
import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SightingDaoImpl implements Dao<Sighting> {

    @Autowired
    JdbcTemplate jdbc;

    //private method to get the hero for each sighting. This assumes that there can be only one hero per sighting which
    //may or may not be the desired approach
    private Hero getHeroForSighting(int sightingId) {
        //Surround with try catch because of the possibility of the queryForObject function 
        //returning a null value if there is no sighting with that id or hero associated with that sightingId
        try {
            //Uses the alias for each table to shorten the sql
            //Because the sighting table contains the hero id and none of the other hero attributes - it is necessary to do a join 
            //and retrieve all the hero fields in the hero table to populate our hero object
            final String SELECT_HERO = "SELECT h.* FROM Sighting s JOIN Hero h ON s.HeroId = h.HeroId WHERE SightingId=?";
            Hero hero = jdbc.queryForObject(SELECT_HERO, new HeroMapper(), sightingId);
            
            return hero;
        } catch (DataAccessException e) {
            return null;
        }
    }

    private Location getLocationForSighting(int sightingId) {
        try {
            //see comments for getHeroForSighting()
            final String SELECT_LOCATION = "SELECT l.* FROM Sighting s JOIN Location l ON s.LocationId = l.LocationId WHERE SightingId=?";
            Location location = jdbc.queryForObject(SELECT_LOCATION, new LocationMapper(), sightingId);
            return location;
        } catch (DataAccessException e) {
            return null;
        }
    }

    //To add the Hero and Location object as properties for each sighting object
    private void associateHeroLocation(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            int sightingId = sighting.getSightingId();
            Hero hero = getHeroForSighting(sightingId);
            Location location = getLocationForSighting(sightingId);
            sighting.setHero(hero);
            sighting.setLocation(location);
        }
    }

    @Override
    @Transactional
    public Sighting create(Sighting model) {
        try{
        final String INSERT_SIGHTING = "INSERT INTO Sighting(Date, LocationId, HeroId) VALUES (?,?,?)";
        int locationId = model.getLocation().getLocationId();
        int heroId = model.getHero().getHeroId();
        jdbc.update(INSERT_SIGHTING, model.getDateTime(), locationId, heroId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setSightingId(newId);
        
        return model;
        } catch(Exception e){
            return null;
        }
     
    }

    @Override
    public List<Sighting> readAll() {
        final String SELECT_ALL_SIGHTING = "Select * From Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTING, new SightingMapper());
        associateHeroLocation(sightings);
        return sightings;
    }

    @Override
    public Sighting readById(int id) {
        try {
            final String SELECT_SIGHTING = "Select SightingId, DateAndTime From Sighting WHERE SightingId = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING, new SightingMapper(), id);

            Hero hero = getHeroForSighting(id);
            Location location = getLocationForSighting(id);

            sighting.setHero(hero);
            sighting.setLocation(location);

            return sighting;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Sighting model) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET DateAndTime = ?, LocationId = ?, HeroId =? WHERE SightingId = ?";
        int locationId = model.getLocation().getLocationId();
        int heroId = model.getHero().getHeroId();
        jdbc.update(UPDATE_SIGHTING, model.getDateTime(), locationId, heroId, model.getSightingId());
    }

    @Override
    public void delete(int id) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE SightingId = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }
    
//    private void insertLocationToSighting(Sighting model) {
//        List<Location> places = (List<Location>) model.getLocation();
//        for (Location place : places) {
//            final String INSERT_LOC_SIGHTING = "INSERT INTO Sighting(LocationId) VALUES (?);";
//            jdbc.update(INSERT_LOC_SIGHTING, place.getLocationId(), model.getSightingId());
//        }
//    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("SightingId"));
            sighting.setDateTime(rs.getObject("DateAndTime", LocalDateTime.class));
            return sighting;
        }
    }
}
