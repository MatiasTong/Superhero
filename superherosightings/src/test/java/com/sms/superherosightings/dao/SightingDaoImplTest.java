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
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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

    /**
     * Test of create method, of class SightingDaoImpl.
     */
    @Test
    public void testCreateAndReadByIdSighting() {
        //Must create the location and hero objects to add as attributes for the sighting object
        Hero hero = new Hero();
        hero.setName("hero name");
        hero.setSuperpower("Super Strength");
        hero.setType("Superhero");
        hero.setDescription("Test Description");
        hero = heroDao.create(hero);

        Location location = new Location();
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.7184);
        location.setLongitude(73.9280);
        location.setName("test name");
        location.setState("NY");
        location.setZip(99999);
        location = locationDao.create(location);
        

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        LocalDateTime now = LocalDateTime.now();
        sighting.setDateTime(now);
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);

        Sighting fromDao = sightingDao.readById(sighting.getSightingId());
        assertEquals(fromDao.getLocation(), sighting.getLocation());
        assertEquals(fromDao.getHero(), sighting.getHero());
        assertEquals(fromDao.getDateTime(), sighting.getDateTime());
                
        assertEquals(fromDao, sighting);
    }

    /**
     * Test of readAll method, of class SightingDaoImpl.
     */
    @Test
    public void testReadAll() {
        Location location = new Location();
        location.setLocationId(1);
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);

        location.setLongitude(-73.928017);

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
        sighting.setDateTime(LocalDateTime.now());
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setHero(hero);
        sighting2.setDateTime(LocalDateTime.now());
        sighting2.setLocation(location);
        sighting2 = sightingDao.create(sighting2);

        List<Sighting> sightings = sightingDao.readAll();

        assertEquals(sightings.size(), 0);
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));

    }

    /**
     * Test of update method, of class SightingDaoImpl.
     */
    @Test
    public void testUpdate() {
        Location location = new Location();
        location.setLocationId(1);
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);

        location.setLongitude(-73.928017);

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

        //assert the newly created sighting is the one currently in the database
        sighting.setHero(hero);
        sighting.setDateTime(LocalDateTime.now());
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);
        Sighting fromDao = sightingDao.readById(sighting.getSightingId());
        assertEquals(fromDao, sighting);

        //Before calling the update function, assert that the sighting with updates 
        //is different from the one currently in the database
        Hero hero2 = new Hero();
        hero2.setDescription("test description 2");
        hero2.setHeroId(2);
        hero2.setName("test name 2");
        hero2.setSuperpower("test superpower 2");
        hero2.setType("test type 2");

        sighting.setHero(hero2);
        assertNotEquals(fromDao, sighting);

        //After calling update, assert that the sighting 
        //currently in the database is the updated version/
        sightingDao.update(sighting);
        fromDao = sightingDao.readById(sighting.getSightingId());
        assertEquals(fromDao, sighting);
    }

    /**
     * Test of delete method, of class SightingDaoImpl.
     */
    @Test
    public void testDelete() {
        Location location = new Location();
        location.setLocationId(1);
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);

        location.setLongitude(-73.928017);

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
        sighting.setDateTime(LocalDateTime.now());
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);

        Sighting fromDao = sightingDao.readById(sighting.getSightingId());
        assertEquals(fromDao, sighting);

        sightingDao.delete(sighting.getSightingId());
        assertNull(sightingDao.readById(sighting.getSightingId()));
    }

}
