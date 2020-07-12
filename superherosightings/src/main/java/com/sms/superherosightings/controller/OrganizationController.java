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
import com.sms.superherosightings.model.Organization;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author matiastong
 */
@Controller
public class OrganizationController {

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

    /*The ConstraintViolation object holds information about the error; 
specifically, each one will hold the message of a validation error it found.*/
    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayorgs(Model model) {
        List<Organization> orgs = organizationDao.readAll();
        List<Location> locations = locationDao.readAll();
        List<Hero> heroes = heroDao.readAll();
        model.addAttribute("organizations", orgs);
        model.addAttribute("errors", violations);
        model.addAttribute("locations", locations);
        model.addAttribute("heroes", heroes);

        //Clear errors
        violations = new HashSet<>();
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrg(Organization organization, HttpServletRequest request) {

        String[] heroIds = request.getParameterValues("heroId");
        String locationId = request.getParameter("locationId");

        organization.setLocation(locationDao.readById(Integer.parseInt(locationId)));

        List<Hero> heroes = new ArrayList<>();
        for (String heroId : heroIds) {
            heroes.add(heroDao.readById(Integer.parseInt(heroId)));
        }

        organization.setHeroes(heroes);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {
            organizationDao.create(organization);
        }

        return "redirect:/organizations";

    }

    @GetMapping("organizationDetail")
    public String orgDetail(Integer id, Model model) {
        Organization organization = organizationDao.readById(id);
        model.addAttribute("organization", organization);
        return "organizationDetail";
    }

    @GetMapping("deleteOrg")
    public String deleteOrg(Integer id) {
        organizationDao.delete(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrg(Integer id, Model model) {
        Organization org = organizationDao.readById(id);
        List<Hero> heroes = heroDao.readAll();
        List<Location> locations = locationDao.readAll();
        model.addAttribute("org", org);
        //added errors here
        model.addAttribute("errors", violations);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrg(Organization organization, HttpServletRequest request) {
        String locationId = request.getParameter("locationId");
        String[] heroIds = request.getParameterValues("heroId");

        organization.setLocation(locationDao.readById(Integer.parseInt(locationId)));

        List<Hero> heroes = new ArrayList<>();
        for (String heroId : heroIds) {
            heroes.add(heroDao.readById(Integer.parseInt(heroId)));
        }
        organization.setHeroes(heroes);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {

            organizationDao.update(organization);
        }
        return "redirect:/organizations";
    }
}
