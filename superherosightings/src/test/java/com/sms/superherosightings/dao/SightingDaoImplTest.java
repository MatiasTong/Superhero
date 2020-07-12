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
import com.sms.superherosightings.model.Superpower;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class SightingDaoImplTest {

    @Autowired
    HeroDaoImpl heroDao;

    @Autowired
    LocationDaoImpl locationDao;

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Autowired
    SightingDaoImpl sightingDao;
    
    @Autowired
    SuperpowerDaoImpl superpowerDao;

    public SightingDaoImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        //Delete all rows related to Hero, location, oragnization, and sighting
        List<Hero> heroes = heroDao.readAll();
        for (Hero hero : heroes) {
            heroDao.delete(hero.getHeroId());
        }

        List<Organization> organizations = organizationDao.readAll();
        for (Organization organization : organizations) {
            organizationDao.delete(organization.getOrganizationId());
        }

        List<Sighting> sightings = sightingDao.readAll();
        for (Sighting sighting : sightings) {
            sightingDao.delete(sighting.getSightingId());
        }
        
        List<Superpower> superpowers = superpowerDao.readAll();
        for(Superpower superpower: superpowers){
            superpowerDao.delete(superpower.getSuperpowerId());
        }
        
          List<Location> locations = locationDao.readAll();
        for(Location location: locations){
            locationDao.delete(location.getLocationId());
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
        setUp();
        //Must create the superhero, location, and hero objects to add as attributes for the hero and sighting object
        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);
        
        Hero hero = new Hero();
        hero.setName("hero name");
        hero.setSuperpower(superpower);
        hero.setType("Superhero");
        hero.setDescription("Test Description");
        hero = heroDao.create(hero);

        Location location = new Location();
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);
        location.setLongitude(73.928017);
        location.setName("test name");
        location.setState("NJ");
        location.setZip("99999");
        location = locationDao.create(location);

        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);

        Sighting fromDao = sightingDao.readById(sighting.getSightingId());

        assertEquals(fromDao.getDateTime().truncatedTo(ChronoUnit.SECONDS), sighting.getDateTime().truncatedTo(ChronoUnit.SECONDS));
        assertEquals(fromDao.getHero(), sighting.getHero());
        assertEquals(fromDao.getLocation(), sighting.getLocation());

        assertEquals(fromDao, sighting);
    }

    /**
     * Test of readAll method, of class SightingDaoImpl.
     */
    @Test
    public void testReadAll() {
        setUp();
        Location location = new Location();
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);
        location.setLongitude(73.928017);
        location.setName("test name");
        location.setState("NJ");
        location.setZip("99999");
        location = locationDao.create(location);

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);
        
        Hero hero = new Hero();
        hero.setDescription("test description");
        hero.setName("test name");
        hero.setSuperpower(superpower);
        hero.setType("test type");

        hero = heroDao.create(hero);


        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setHero(hero);
        sighting2.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        sighting2.setLocation(location);
        sighting2 = sightingDao.create(sighting2);

        List<Sighting> sightings = sightingDao.readAll();

        assertEquals(sightings.size(), 2);

        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));

    }

    /**
     * Test of update method, of class SightingDaoImpl.
     */
    @Test
    public void testUpdate() {
        Location location = new Location();
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);
        location.setLongitude(73.928017);
        location.setName("test name");
        location.setState("NJ");
        location.setZip("99999");

        location = locationDao.create(location);

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);
        
        Hero hero = new Hero();
        hero.setDescription("test description");
        hero.setName("test name");
        hero.setSuperpower(superpower);
        hero.setType("test type");

        hero = heroDao.create(hero);


        Sighting sighting = new Sighting();

        //assert the newly created sighting is the one currently in the database
        sighting.setHero(hero);
        sighting.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);
        Sighting fromDao = sightingDao.readById(sighting.getSightingId());
        assertEquals(fromDao, sighting);

        //Before calling the update function, assert that the sighting with updates 
        //is different from the one currently in the database
        Superpower superpower2 = new Superpower();
        superpower2.setSuperpower("Superstrength");
        superpower2.setDescription("The ability to move a ton of things");
        superpower2 = superpowerDao.create(superpower2);
        
        Hero hero2 = new Hero();
        hero2.setDescription("test description 2");
        hero2.setName("test name 2");
        hero2.setSuperpower(superpower2);
        hero2.setType("test type 2");

        hero2 = heroDao.create(hero2);


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
        location.setAddress("test address");
        location.setCity("test city");
        location.setDescription("test description");
        location.setLatitude(40.718464);
        location.setLongitude(73.928017);
        location.setName("test name");
        location.setState("NJ");
        location.setZip("99999");

        location = locationDao.create(location);
        
        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);
        
        Hero hero = new Hero();
        hero.setDescription("test description");
        hero.setName("test name");
        hero.setSuperpower(superpower);
        hero.setType("test type");
        hero = heroDao.create(hero);


        Sighting sighting = new Sighting();
        sighting.setHero(hero);
        sighting.setDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        sighting.setLocation(location);
        sighting = sightingDao.create(sighting);

        Sighting fromDao = sightingDao.readById(sighting.getSightingId());
        assertEquals(fromDao, sighting);

        sightingDao.delete(sighting.getSightingId());
        assertNull(sightingDao.readById(sighting.getSightingId()));
    }

}
