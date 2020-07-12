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
     * Test of create method, of class OrganizationDaoImpl.
     */
    @Test
    public void testAddOrg() {
        /* ARRANGE - setting up my heroes list to then later be added to my 
        organization
         */
        setUp();

        List<Hero> heroes = new ArrayList<>();

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower(superpower);
        superHero.setType("hero");
        superHero = heroDao.create(superHero);

        heroes.add(superHero);

        Location location = new Location();
        location.setName("City Bank");
        location.setDescription("Busiest bank in the city");
        location.setAddress("123 cashmoney street");
        location.setCity("NYC");
        location.setState("NY");
        location.setZip("11104");
        location.setLatitude(0.01);
        location.setLongitude(0.01);
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

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower(superpower);
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
        location.setZip("11104");
        location.setLatitude(0.01);
        location.setLongitude(0.01);
        location = locationDao.create(location);

        Organization org1 = new Organization();
        org1.setName("Classic Heroes");
        org1.setEmail("classicheroes@gmail.com");
        org1.setType("hero");
        org1.setHeroes(heroes);
        org1.setDescription("the best group");
        org1.setLocation(location);
        org1 = organizationDao.create(org1);

        Organization org2 = new Organization();
        org2.setName("Classic Heroes");
        org2.setEmail("classicheroes@gmail.com");
        org2.setType("hero");
        org2.setHeroes(heroes);
        org2.setDescription("the best group");
        org2.setLocation(location);
        org2 = organizationDao.create(org1);

        List<Organization> organizations = organizationDao.readAll();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(org1));

    }

    /**
     * Test of update method, of class OrganizationDaoImpl.
     */
    @Test
    public void testUpdateOrg() {
        setUp();
        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower(superpower);
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
        location.setZip("11104");
        location.setLatitude(0.01);
        location.setLongitude(0.01);
        location = locationDao.create(location);

        Organization org = new Organization();
        org.setName("Classic Heroes");
        org.setEmail("classicheroes@gmail.com");
        org.setType("hero");
        org.setHeroes(heroes);
        org.setDescription("the best group");
        org.setLocation(location);
        org = organizationDao.create(org);

        Organization fromDao = organizationDao.readById(org.getOrganizationId());

        assertEquals(org, fromDao);

        org.setEmail("classicheroes123@gmail.com");

        organizationDao.update(org);

        assertNotEquals(fromDao, org);
    }

    /**
     * Test of delete method, of class OrganizationDaoImpl.
     */
    @Test
    public void testDeleteOrgById() {
        setUp();

        Superpower superpower = new Superpower();
        superpower.setSuperpower("Superstrength");
        superpower.setDescription("The ability to move a ton of things");
        superpower = superpowerDao.create(superpower);

        Hero superHero = new Hero();
        superHero.setName("Spiderman");
        superHero.setDescription("classic");
        superHero.setSuperpower(superpower);
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
        location.setZip("11104");
        location.setLatitude(0.01);
        location.setLongitude(0.01);
        location = locationDao.create(location);

        Organization org = new Organization();
        org.setName("Classic Heroes");
        org.setEmail("classicheroes@gmail.com");
        org.setType("hero");
        org.setHeroes(heroes);
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
