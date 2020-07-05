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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SuperpowerDaoImpl implements Dao<Superpower> {

@Autowired
JdbcTemplate jdbc;
    
    @Override
    public Superpower create(Superpower model) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(Superpower, Description) VALUES (?,?)";
        jdbc.update(INSERT_SUPERPOWER, model.getSuperpower(), model.getDescription());
        int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);
        model.setSuperpowerId(newId);
        return model;
    }

    @Override
    public List<Superpower> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Superpower readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Superpower model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Hero> getHeroesForSuperpower(Superpower model){
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
            superpower.setDescription(rs.getString("Superpower"));
            superpower.setDescription(rs.getString("Description"));
            return superpower;
        }

    }
    
}
