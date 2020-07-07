/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.controller;

import com.sms.superherosightings.dao.HeroDaoImpl;
import com.sms.superherosightings.dao.LocationDaoImpl;
import com.sms.superherosightings.dao.OrganizationDaoImpl;
import com.sms.superherosightings.dao.SightingDaoImpl;
import com.sms.superherosightings.dao.SuperpowerDaoImpl;
import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author matiastong
 */
@Controller
public class SightingController {

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

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.readAll();
        List<Location> locations = locationDao.readAll();
        List<Hero> heroes = heroDao.readAll();
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("heroes", heroes);

        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        String locationId = request.getParameter("locationId");
        String heroId = request.getParameter("heroId");
        String dateTimeAsString = request.getParameter("dateTime");
//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm"); 
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeAsString);

        Sighting sighting = new Sighting();
        sighting.setDateTime(dateTime);
        sighting.setLocation(locationDao.readById(Integer.parseInt(locationId)));
        sighting.setHero(heroDao.readById(Integer.parseInt(heroId)));
        sightingDao.create(sighting);
        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.delete(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        Sighting sighting = sightingDao.readById(id);
        List<Location> locations = locationDao.readAll();
        List<Hero> heroes = heroDao.readAll();
       
        model.addAttribute(sighting);
        model.addAttribute("locations", locations);
        model.addAttribute("heroes", heroes);
        return "editSighting";
    }

}
