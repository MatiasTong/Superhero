/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;



public class HeroDaoImpl implements Dao<Hero> {
   
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Hero Create(Hero model) {
       final String INSERT_HERO = "INSERT INTO Hero(HeroId, `Name`,`Description`,Specialty,`Type) VALUES (?,?,?,?,?);";
       jdbc.update(INSERT_HERO, model.getHeroId(),model.getName(),model.getDescription(),model.getSuperpower(), model.getType());
       int newId = jdbc.queryForObject("SELECT Last_Insert_Id()", Integer.class);
       model.setHeroId(newId);
       return model;
    }
    
    private void insertHeroOrganization()

    @Override
    public List<Hero> ReadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hero ReadById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Hero model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<Organization> getOrganizationsByHero(int heroId){
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void associateOrganizations(int heroId, List<Organization> organization){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
