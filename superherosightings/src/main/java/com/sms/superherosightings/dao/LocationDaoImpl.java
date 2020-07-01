/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDaoImpl implements Dao<Location> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location create(Location model) {
        final String INSERT_LOCATION = "INSERT INTO Location(Name, Description, Address, City, State, ZipCode, Lat , `Long`) VALUES (?,?,?,?,?,?,?,?); ";

        jdbc.update(INSERT_LOCATION, model.getName(), model.getDescription(), model.getAddress(), model.getCity(), model.getState(), model.getZip(), model.getLatitude(), model.getLongitude());

        int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);

        model.setLocationId(newId);

        return model;
    }

    @Override
    public List<Location> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Location model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class LocationMapper implements RowMapper<Location> {

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
