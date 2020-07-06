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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
 * @author sangyec
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoImplTest {
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

    public LocationDaoImplTest() {
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

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class LocationDaoImpl.
     */
    @Test
    public void testCreateAndReadLocationById() {
        //Arrange
        setUp();
        Location location = new Location();
        location.setName("Location name");
        location.setDescription("Description");
        location.setAddress("Address");
        location.setCity("City");
        location.setState("NY");
        location.setZip(11323);
        location.setLatitude(90.45);
        location.setLongitude(123.45);

        //Act
        location = locationDao.create(location);
        Location fromDao = locationDao.readById(location.getLocationId());

        //Assert
        assertEquals(location, fromDao);

    }

    /**
     * Test of readAll method, of class LocationDaoImpl.
     */
    @Test
    public void testReadAll() {
        //Arrange
        setUp();

        Location location = new Location();
        location.setName("Location name");
        location.setDescription("Description");
        location.setAddress("Address");
        location.setCity("City");
        location.setState("NY");
        location.setZip(11323);
        location.setLatitude(90.45);
        location.setLongitude(123.45);
        location = locationDao.create(location);

        Location secondLocation = new Location();
        secondLocation.setName("Second location");
        secondLocation.setDescription("Second Location Description");
        secondLocation.setAddress("Second  Address");
        secondLocation.setCity("Second City");
        secondLocation.setState("NY");
        secondLocation.setZip(11323);
        secondLocation.setLatitude(90.45);
        secondLocation.setLongitude(123.45);
        secondLocation = locationDao.create(secondLocation);

        //Act
        List<Location> locations = locationDao.readAll();

        //Assert
        assertEquals(locations.size(), 2);
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(secondLocation));
    }

  
    /**
     * Test of update method, of class LocationDaoImpl.
     */
    @Test
    public void testUpdate() {
        //Arrange
        setUp();

        Location location = new Location();
        location.setName("Location name");
        location.setDescription("Description");
        location.setAddress("Address");
        location.setCity("City");
        location.setState("NY");
        location.setZip(11323);
        location.setLatitude(90.45);
        location.setLongitude(123.45);
        location = locationDao.create(location);

        Location fromDao = locationDao.readById(location.getLocationId());
        assertEquals(location, fromDao);

        location.setLocationId(location.getLocationId());
        location.setName("Location updated");
        location.setDescription("Description Updated");
        location.setAddress("Address Updated");
        location.setCity("City Updated");
        location.setState("NY");
        location.setZip(11323);
        location.setLatitude(90.45);
        location.setLongitude(123.45);

        locationDao.update(location);
        assertNotEquals(location, fromDao);

        fromDao = locationDao.readById(location.getLocationId());
        assertEquals(location, fromDao);

    }

    /**
     * Test of delete method, of class LocationDaoImpl.
     */
    @Test
    public void testDelete() {
        //Arrange
        setUp();

        Location location = new Location();
        location.setName("Location name");
        location.setDescription("Description");
        location.setAddress("Address");
        location.setCity("City");
        location.setState("NY");
        location.setZip(11323);
        location.setLatitude(90.45);
        location.setLongitude(123.45);
        location = locationDao.create(location);
        
        Location fromDao = locationDao.readById(location.getLocationId());
        assertEquals(location, fromDao);
        
        locationDao.delete(location.getLocationId());
        
        fromDao = locationDao.readById(location.getLocationId());
        assertNull(fromDao);
        
        List<Location>locations = locationDao.readAll();
        assertEquals(locations.size(), 0);
    }

}
