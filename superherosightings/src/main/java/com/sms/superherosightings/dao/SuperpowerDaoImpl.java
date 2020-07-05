/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.dao.HeroDaoImpl.HeroMapper;
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

@Repository
public class SuperpowerDaoImpl implements Dao<Superpower> {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower create(Superpower model) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(Power, `Description`) VALUES (?,?)";
        jdbc.update(INSERT_SUPERPOWER, model.getSuperpower(), model.getDescription());
        int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);
        model.setSuperpowerId(newId);
        return model;
    }

    @Override
    public List<Superpower> readAll() {
        final String GET_ALL_SUPERPOWER = "SELECT * FROM Superpower;";
        List<Superpower> superpowers = jdbc.query(GET_ALL_SUPERPOWER, new SuperpowerMapper());
        return superpowers;
    }

    @Override
    public Superpower readById(int id) {
        try {
            final String GET_SUPERPOWER = "SELECT * FROM Superpower WHERE SuperpowerId = ?;";
            Superpower superpower = jdbc.queryForObject(GET_SUPERPOWER, new SuperpowerMapper(), id);
            return superpower;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Superpower model) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpower SET Power = ?, `Description` = ? WHERE SuperpowerId = ? ";
        jdbc.update(UPDATE_SUPERPOWER, model.getSuperpower(), model.getDescription(), model.getSuperpowerId());

    }

    @Override
    public void delete(int id) {
        final String DELETE_HERO = "DELETE FROM Hero WHERE SuperpowerId = ?;";
        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE SuperpowerId = ?;";

        jdbc.update(DELETE_HERO, id);
        jdbc.update(DELETE_SUPERPOWER, id);

    }

    public List<Hero> getHeroesForSuperpower(Superpower model) {
        final String SELECT_HEROES_FOR_SUPERPOWER = "SELECT h.* FROM Hero h "
                + "JOIN Superpower s ON h.SuperpowerId "
                + "= s.SuperpowerId WHERE s.SuperpowerId = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_SUPERPOWER,
                new HeroMapper(), model.getSuperpowerId());
        return heroes;
    }

    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int arg1) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerId(rs.getInt("SuperpowerId"));
            superpower.setSuperpower(rs.getString("Power"));
            superpower.setDescription(rs.getString("Description"));
            return superpower;
        }

    }

}
