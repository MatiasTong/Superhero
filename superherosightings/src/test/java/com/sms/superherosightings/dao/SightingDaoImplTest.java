/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Sighting;
import com.sms.superherosightings.model.Organization;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author matiastong
 */
public class SightingDaoImplTest {
    
    @Autowired
    HeroDaoImpl heroDao;

    @Autowired
    LocationDaoImpl locationDao;

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Autowired
    SightingDaoImpl sightingDao;
    
    public SightingDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
                //Delete all rows related to Hero, location, oragnization, and sighting
        List<Hero> heroes = heroDao.readAll();
        for (Hero hero : heroes) {
            heroDao.delete(hero.getHeroId());
        }

        List<Location> locations = locationDao.readAll();
        for (Location location : locations) {
            locationDao.delete(location.getLocationId());
        }

        List<Organization> organizations = organizationDao.readAll();
        for (Organization organization : organizations) {
            organizationDao.delete(organization.getOrganizationId());
        }

        List<Sighting> sightings = sightingDao.readAll();
        for (Sighting sighting : sightings) {
            sightingDao.delete(sighting.getSightingId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class SightingDaoImpl.
     */
    @Test
    public void testCreateAndReadByIdSighting() {
        Location location = new Location();
        location.setLocationId(1);
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(new BigDecimal("40.718464"));
        location.setLongitude(new BigDecimal("-73.928017"));
        location.setName("test name");
        location.setState("test state");
        location.setZip(99999);
        
        Hero hero = new Hero();
        hero.setDescription("test description");
        hero.setHeroId(1);
        hero.setName("test name");
        hero.setSuperpower("test superpower");
        hero.setType("test type");
        
        Sighting sighting = new Sighting();
        
        sighting.setHero(hero);
        sighting.setDateTime(LocalDateTime.MIN);
        
        
                
        
    }

    /**
     * Test of readAll method, of class SightingDaoImpl.
     */
    @Test
    public void testReadAll() {
    }

    /**
     * Test of readById method, of class SightingDaoImpl.
     */
    @Test
    public void testReadById() {
    }

    /**
     * Test of update method, of class SightingDaoImpl.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of delete method, of class SightingDaoImpl.
     */
    @Test
    public void testDelete() {
    }
    
}
