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
import com.sms.superherosightings.model.Superpower;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class SuperpowerDaoImplTest {

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

    public SuperpowerDaoImplTest() {
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
        for (Superpower superpower : superpowers) {
            superpowerDao.delete(superpower.getSuperpowerId());
        }

        List<Location> locations = locationDao.readAll();
        for (Location location : locations) {
            locationDao.delete(location.getLocationId());
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class LocationDaoImpl.
     */
    @Test
    public void testCreateAndReadSuperpowerById() {
        //Arrange
        setUp();
        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");

        //Act
        superpower = superpowerDao.create(superpower);
        Superpower fromDao = superpowerDao.readById(superpower.getSuperpowerId());

        //Assert
        assertEquals(superpower, fromDao);

    }

    /**
     * Test of readAll method, of class LocationDaoImpl.
     */
    @Test
    public void testReadAll() {
        //Arrange
        setUp();

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        Superpower superpower2 = new Superpower();
        superpower2.setSuperpower("Superspeed");
        superpower2.setDescription("The ability to move fast");
        superpower2 = superpowerDao.create(superpower2);

        //Act
        List<Superpower> superpowers = superpowerDao.readAll();

        //Assert
        assertEquals(superpowers.size(), 2);
        assertTrue(superpowers.contains(superpower));
        assertTrue(superpowers.contains(superpower2));
    }

    /**
     * Test of update method, of class LocationDaoImpl.
     */
    @Test
    public void testUpdate() {
        //Arrange
        setUp();

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        //Act
        Superpower fromDao = superpowerDao.readById(superpower.getSuperpowerId());

        superpower.setSuperpower("Superspeed");
        superpower.setDescription("The ability to move very a fast");

        superpowerDao.update(superpower);
        assertNotEquals(superpower, fromDao);

        fromDao = superpowerDao.readById(superpower.getSuperpowerId());
        assertEquals(superpower, fromDao);

    }

    /**
     * Test of delete method, of class LocationDaoImpl.
     */
    @Test
    public void testDelete() {
        //Arrange
        setUp();

        //Arrange
        setUp();
        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        //Act
        Superpower fromDao = superpowerDao.readById(superpower.getSuperpowerId());

        superpowerDao.delete(superpower.getSuperpowerId());

        fromDao = superpowerDao.readById(superpower.getSuperpowerId());
        assertNull(fromDao);

        List<Superpower> superpowers = superpowerDao.readAll();
        assertEquals(superpowers.size(), 0);
    }

}
