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

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class HeroDaoImpl.
     */
    @Test
    public void testCreateAndReadHeroByID() {
        //Arrange
        setUp();
        Hero hero = new Hero();
        hero.setName("hero name");
        hero.setSuperpower("Super Strength");
        hero.setType("Superhero");
        hero.setDescription("Test Description");
        
        //Act
        hero = heroDao.create(hero); 
        Hero fromDao = heroDao.readById(hero.getHeroId());
        
        //Assert
        assertEquals(hero, fromDao);
    }

    /**
     * Test of readAll method, of class HeroDaoImpl.
     */
    @Test
    public void testReadAll() {
        //Arrange
        setUp();
        
        Hero hero = new Hero();
        hero.setName("hero name");
        hero.setSuperpower("Super Strength");
        hero.setType("Superhero");
        hero.setDescription("Test Description");
        hero = heroDao.create(hero);
        
        Hero hero2 = new Hero();
        hero2.setName("hero name 2");
        hero2.setSuperpower("Super Strength 2");
        hero2.setType("Superhero");
        hero2.setDescription("Test Description2");
        hero2 = heroDao.create(hero2);
        
        //Act
        List<Hero> heroes = heroDao.readAll();
        
        //Assert
        assertEquals(heroes.size(), 2);
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
        
    }

    /**
     * Test of update method, of class HeroDaoImpl.
     */
    @Test
    public void testUpdate() {
        //Arrange
        setUp();
        
        Hero hero = new Hero();
        hero.setName("hero name");
        hero.setSuperpower("Super Strength");
        hero.setType("Superhero");
        hero.setDescription("Test Description");
        hero = heroDao.create(hero);
        
        //assert the newly created sighting is the one currently in the database
        Hero fromDao = heroDao.readById(hero.getHeroId());
        assertEquals(hero, fromDao);
        
        //Before calling the update function, assert that the sighting with updates 
        //is different from the one currently in the database
        hero.setHeroId(hero.getHeroId());
        hero.setName("hero name updated");
        hero.setSuperpower("Super Strength updated");
        hero.setType("Superhero updated");
        hero.setDescription("Test Description updated");
        
        heroDao.update(hero);
        assertNotEquals(hero, fromDao);
        
        //After calling update, assert that the sighting 
        //currently in the database is the updated version/
        fromDao = heroDao.readById(hero.getHeroId());
        assertEquals(hero, fromDao);
        

    }

    /**
     * Test of delete method, of class HeroDaoImpl.
     */
    @Test
    public void testDelete() {
         //Arrange
        setUp();
        
        Hero hero = new Hero();
        hero.setName("hero name");
        hero.setSuperpower("Super Strength");
        hero.setType("Superhero");
        hero.setDescription("Test Description");
        hero = heroDao.create(hero);
        
        Hero fromDao = heroDao.readById(hero.getHeroId());
        assertEquals(hero, fromDao);
        
        heroDao.delete(hero.getHeroId());
        
        fromDao = heroDao.readById(hero.getHeroId());
        assertNull(fromDao);
        
        List<Hero> heroes = heroDao.readAll();
        assertEquals(heroes.size(), 0);
                
    }

}
