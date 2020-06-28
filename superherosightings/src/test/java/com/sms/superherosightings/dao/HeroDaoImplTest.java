/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Organization;
import com.sms.superherosightings.model.Sighting;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author matiastong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroDaoImplTest {

    @Autowired
    HeroDaoImpl heroDao;

    @Autowired
    LocationDaoImpl locationDao;

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Autowired
    SightingDaoImpl sightingDao;

    public HeroDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
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

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class HeroDaoImpl.
     */
    @Test
    public void testCreateAndReadHeroByID() {
        
        
    }

    /**
     * Test of readAll method, of class HeroDaoImpl.
     */
    @Test
    public void testReadAll() {
    }

    /**
     * Test of readById method, of class HeroDaoImpl.
     */
    @Test
    public void testReadById() {
    }

    /**
     * Test of update method, of class HeroDaoImpl.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of delete method, of class HeroDaoImpl.
     */
    @Test
    public void testDelete() {
    }

}
