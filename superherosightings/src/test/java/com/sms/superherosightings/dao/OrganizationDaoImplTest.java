/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Organization;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
 * @author shirl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationDaoImplTest {

    //dependency injection
    @Autowired
    HeroDaoImpl heroDao;

    @Autowired
    LocationDaoImpl locationDao;

    @Autowired
    OrganizationDaoImpl organizationDao;

    public OrganizationDaoImplTest() {
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

        List<Organization> organizations = organizationDao.readAll();
        for (Organization organization : organizations) {
            organizationDao.delete(organization.getOrganizationId());
        }

        List<Location> locations = locationDao.readAll();
        for (Location location : locations) {
            locationDao.delete(location.getLocationId());

        }

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddOrg() {

        Location location = new Location();
        location.setName("Chase Bank");
        location.setAddress("234 Main ave");
        location.setCity("Boston");
        location.setState("MA");
        location.setZip(23415);
        location.setDescription("Hero HQ Underground");
        location.setLatitude(0.1);
        location.setLongitude(0.0);

        location = locationDao.create(location);

        Organization org = new Organization();
        org.setName("Good people");
        org.setDescription("Classic Heroes");
        org.setEmail("goodpeople123@gmail.com");
        org.setType("hero");
        org.setLocation(location);
        org = organizationDao.create(org);

        Organization fromDao = organizationDao.readById(org.getOrganizationId());

        assertEquals(fromDao, org);
    }

    /**
     * Test of create method, of class OrganizationDaoImpl.
     */
    @Test
    public void testAddAndGetOrg() {
        /* ARRANGE - setting up my heroes list to then later be added to my 
        organization
         */
        setUp();
        List<Hero> heroes = new ArrayList<>();

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower("speed");
        superHero.setType("hero");
        superHero = heroDao.create(superHero);

        heroes.add(superHero);

        Location location = new Location();
        location.setName("City Bank");
        location.setDescription("Busiest bank in the city");
        location.setAddress("123 cashmoney street");
        location.setCity("NYC");
        location.setState("NY");
        location.setZip(11104);

        location.setLatitude(0.0);
        location.setLongitude(0.0);
        location = locationDao.create(location);

        //ACT
        Organization org1 = new Organization();

        org1.setName("Classic Heroes");
        org1.setEmail("classicheroes@gmail.com");
        org1.setType("hero");
        org1.setHeroes(heroes);
        org1.setDescription("the best group");
        org1.setLocation(location);

        org1 = organizationDao.create(org1);

        Organization fromDao = organizationDao.readById(org1.getOrganizationId());
        //ASSERT
        assertEquals(org1, fromDao);

    }

    /**
     * Test of readAll method, of class OrganizationDaoImpl.
     */
    @Test
    public void testReadAllOrganizations() {

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower("speed");
        superHero.setType("hero");
        superHero = heroDao.create(superHero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(superHero);

        Location location = new Location();
        location.setName("City Bank");
        location.setDescription("Busiest bank in the city");
        location.setAddress("123 cashmoney street");
        location.setCity("NYC");
        location.setState("NY");
        location.setZip(11104);
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        location = locationDao.create(location);

        Organization org1 = new Organization();
        org1.setName("Classic Heroes");
        org1.setEmail("classicheroes@gmail.com");
        org1.setType("hero");
        org1.setHeroes(heroes);
        org1.setDescription("the best group");
        org1.setLocation(location);
        org1 = organizationDao.create(org1);


        List<Organization> organizations = organizationDao.readAll();
        assertEquals(1, organizations.size());
        assertTrue(organizations.contains(org1));
  
    }

    /**
     * Test of readById method, of class OrganizationDaoImpl.
     */
//    @Test
//    public void testReadById() {
//    }
    /**
     * Test of update method, of class OrganizationDaoImpl.
     */
    @Test
    public void testUpdateOrg() {
        Location location = new Location();
        location.setName("City Bank");
        location.setDescription("Busiest bank in the city");
        location.setAddress("123 cashmoney street");
        location.setCity("NYC");
        location.setState("NY");
        location.setZip(11104);
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        location = locationDao.create(location);

        Organization org = new Organization();
        org.setName("Classic Heroes");
        org.setEmail("classicheroes@gmail.com");
        org.setType("hero");
        org.setDescription("the best group");
        org.setLocation(location);
        org = organizationDao.create(org);

        Organization fromDao = organizationDao.readById(org.getOrganizationId());

        assertEquals(fromDao, org);

        org.setName("Classic Heroes");
        org.setEmail("classicheroes123@gmail.com");

        organizationDao.update(org);

        assertNotEquals(fromDao, org);
    }

    /**
     * Test of delete method, of class OrganizationDaoImpl.
     */
    @Test
    public void testDeleteOrgById() {
        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower("speed");
        superHero.setType("hero");
        superHero = heroDao.create(superHero);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(superHero);

        Location location = new Location();
        location.setName("City Bank");
        location.setDescription("Busiest bank in the city");
        location.setAddress("123 cashmoney street");
        location.setCity("NYC");
        location.setState("NY");
        location.setZip(11104);
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        location = locationDao.create(location);

        Organization org = new Organization();
        org.setName("Classic Heroes");
        org.setEmail("classicheroes@gmail.com");
        org.setType("hero");
        org.setDescription("the best group");
        org.setLocation(location);
        org = organizationDao.create(org);

        Organization org2 = new Organization();
        org2.setName("Good people");
        org2.setDescription("Classic Heroes");
        org2.setHeroes(heroes);
        org2.setEmail("goodpeople123@gmail.com");
        org2.setType("hero");
        org2.setLocation(location);
        org2 = organizationDao.create(org2);

        List<Organization> organizations = organizationDao.readAll();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(org));
        assertTrue(organizations.contains(org2));

        organizationDao.delete(org.getOrganizationId());
        organizations = organizationDao.readAll();

        assertEquals(1, organizations.size());
        assertTrue(organizations.contains(org2));
        assertFalse(organizations.contains(org));
    }

}
