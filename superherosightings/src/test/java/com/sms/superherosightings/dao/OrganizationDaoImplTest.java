/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.dao;

import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Organization;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    SightingDaoImpl sightingDao;

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

        List<Location> locations = locationDao.readAll();
        for (Location location : locations) {
            locationDao.delete(location.getLocationId());
        }

        List<Organization> organizations = organizationDao.readAll();
        for (Organization organization : organizations) {
            organizationDao.delete(organization.getOrganizationId());
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of create method, of class OrganizationDaoImpl.
     */
    @Test
    public void testCreate() {
        /* ARRANGE - setting up my heroes list to then later be added to my 
        organization
         */
        List<Hero> heroes = new ArrayList<>();

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setType("hero");
        heroDao.create(superHero);

        heroes.add(superHero);

        Location location = new Location();
        location.setName("City Bank");
        location.setDescription("Busiest bank in the city");
        location.setAddress("123 cashmoney street");
        location.setCity("NYC");
        location.setState("NY");
        location.setZip(11104);
        location.setLatitude(0.1);
        location.setLongitude(0.0);
        
        //ACT
        Organization org1 = new Organization();

        org1.setName("Classic Heroes");
        org1.setEmail("classicheroes@gmail.com");
        org1.setType("hero");
        org1.setHeroes(heroes);
        org1.setDescription("the best group");
        org1.setLocation(location);

        organizationDao.create(org1);

        Organization fromDao = organizationDao.readById(org1.getOrganizationId());
        //ASSERT
        assertEquals(fromDao, org1);

    }

    /**
     * Test of readAll method, of class OrganizationDaoImpl.
     */
    @Test
    public void testReadAll() {
    }

    /**
     * Test of readById method, of class OrganizationDaoImpl.
     */
    @Test
    public void testReadById() {
    }

    /**
     * Test of update method, of class OrganizationDaoImpl.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of delete method, of class OrganizationDaoImpl.
     */
    @Test
    public void testDelete() {
    }

}
