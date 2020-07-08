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
import com.sms.superherosightings.model.Location;
import com.sms.superherosightings.model.Superpower;
import java.util.List;
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
public class LocationController {
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
    
    @GetMapping("locations")
    public String displayLocations(Model model){
        List<Location> locations = locationDao.readAll();
        model.addAttribute("locations", locations );
        return "locations";
    }
    
    @PostMapping("addLocation")
      public String addLocation(Location location) {
          locationDao.create(location);
          return "redirect:/locations";
      }    
      
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id){
        locationDao.delete(id);
        return "redirect:/locations";
        
    }
    
    @GetMapping("editLocation")
    public String editSuperpower(Integer id, Model model) {
        Location location = locationDao.readById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }
    
    @PostMapping("editLocation")
    public String performEditLocation(Location location) {
        locationDao.update(location);
        return "redirect:/locations";
    }
    
}
