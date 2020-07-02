/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Sighting;
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
public class LocationDaoImpl implements Dao<Location> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
<<<<<<< HEAD

    public Location create(Location model) {
        try {
            final String INSERT_LOCATION = "INSERT INTO Location(`Name`, `Description`, Address, City, State, ZipCode, Lat, `Long`) VALUES (?,?,?,?,?,?,?,?); ";

            jdbc.update(INSERT_LOCATION, model.getName(), model.getDescription(), model.getAddress(), model.getCity(), model.getState(), model.getZip(), model.getLatitude(), model.getLongitude());

            int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);

            model.setLocationId(newId);
=======
    public Location create(Location model) {

        final String INSERT_LOCATION = "INSERT INTO Location(`Name`, `Description`, Address, City, State, ZipCode, Lat , `Long`) VALUES (?,?,?,?,?,?,?,?); ";
        jdbc.update(INSERT_LOCATION, model.getName(), model.getDescription(), model.getAddress(), model.getCity(), model.getState(), model.getZip(), model.getLatitude(), model.getLongitude());

        int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);
        model.setLocationId(newId);
>>>>>>> 936cc9f6b44d88286e72038e50f0df8fc98b8cc8

            return model;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Location> readAll() {
        final String SELECT_ALL_LOCATION = "SELECT * FROM Location";
        List<Location> location = jdbc.query(SELECT_ALL_LOCATION, new LocationMapper());
        return location;
    }

    @Override
    public Location readById(int id) {
        try {
            final String SELECT_LOCATION = "SELECT * FROM Location WHERE LocationId= ?";
            Location location = jdbc.queryForObject(SELECT_LOCATION, new LocationMapper(), id);
            return location;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Location model) {
<<<<<<< HEAD
        final String UPDATE_LOCATION = "UPDATE Location SET `Name` = ?,`Description` = ?, Address = ?, City = ?,State = ?,Zip=?,Latitude=?,Longitude=? WHERE LocationId = ?;";
        jdbc.update(UPDATE_LOCATION, model.getName(), model.getDescription(), model.getAddress(), model.getCity(), model.getState(), model.getZip(), model.getLatitude(), model.getLongitude());

=======
        final String UPDATE_LOCATION = "UPDATE Location SET `Name` = ?,`Description` = ?, Address = ?, City = ?,State = ?,ZipCode=?,Lat=?,`Long`=? WHERE LocationId = ?;";
        jdbc.update(UPDATE_LOCATION, model.getName(), model.getDescription(), model.getAddress(), model.getCity(), model.getState(), model.getZip(), model.getLatitude(), model.getLongitude(), model.getLocationId());
        
>>>>>>> 936cc9f6b44d88286e72038e50f0df8fc98b8cc8
    }

    @Override
    public void delete(int id) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE LocationId =?";
        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE LocationId=?";
        final String DELETE_LOCATION = "DELETE FROM Location WHERE LocationId=?";

        jdbc.update(DELETE_SIGHTING, id);
        jdbc.update(DELETE_ORGANIZATION, id);
        jdbc.update(DELETE_LOCATION, id);

    }
<<<<<<< HEAD

    public static final class LocationMapper implements RowMapper<Location> {

=======
    
    private void insertLocationToSighting(Sighting model) {
        List<Location> places = (List<Location>) model.getLocation();
        for (Location place : places) {
            final String INSERT_LOC_SIGHTING = "INSERT INTO Sighting(LocationId) VALUES (?);";
            jdbc.update(INSERT_LOC_SIGHTING, place.getLocationId(), model.getSightingId());
        }
    }

    public static final class LocationMapper implements RowMapper<Location>{
        
>>>>>>> 936cc9f6b44d88286e72038e50f0df8fc98b8cc8
        @Override

        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("LocationId"));
            location.setName(rs.getString("Name"));
            location.setDescription(rs.getString("Description"));
            location.setAddress(rs.getString("Address"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZip(rs.getInt("ZipCode"));
            location.setLatitude(rs.getDouble("Lat"));
            location.setLongitude(rs.getDouble("Long"));

            return location;
        }

    }
}
