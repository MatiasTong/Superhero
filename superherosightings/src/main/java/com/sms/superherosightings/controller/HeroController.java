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

import com.sms.superherosightings.model.Sighting;

import com.sms.superherosightings.model.Organization;

import com.sms.superherosightings.model.Superpower;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
public class HeroController {
   /*The ConstraintViolation object holds information about the error; 
specifically, each one will hold the message of a validation error it found.*/
    Set<ConstraintViolation<Hero>> violations = new HashSet<>();

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

 
  

    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Hero> heroes = heroDao.readAll();
        List<Superpower> superpowers = superpowerDao.readAll();

        model.addAttribute("Superpowers", superpowers);
        model.addAttribute("Heroes", heroes);
        model.addAttribute("errors", violations);

        //Clear errors
        violations = new HashSet<>();

        return "heroes";
    }

    @PostMapping("addHero")
    public String addHero(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String superpowerId = request.getParameter("superpowerId");
        String type = request.getParameter("type");

        Superpower superpower = superpowerDao.readById(Integer.parseInt(superpowerId));

        Hero hero = new Hero();
        hero.setName(name);
        hero.setDescription(description);
        hero.setSuperpower(superpower);
        hero.setType(type);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            heroDao.create(hero);
        }
        return "redirect:/heroes";
    }

    @GetMapping("deleteHero")
    public String deleteHero(Integer id
    ) {
        heroDao.delete(id);
        return "redirect:/heroes";
    }

    @GetMapping("editHero")
    public String editHero(Integer id, Model model
    ) {
        Hero hero = heroDao.readById(id);
        List<Superpower> superpowers = superpowerDao.readAll();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("hero", hero);
        model.addAttribute("errors", violations);

        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(Hero hero, Integer superpowerId) {

        Superpower superpower = superpowerDao.readById(superpowerId);
        hero.setSuperpower(superpower);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            heroDao.update(hero);
        }
        return "redirect:/heroes";

    }

}
