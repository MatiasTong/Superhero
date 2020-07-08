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
import com.sms.superherosightings.model.Organization;
import com.sms.superherosightings.model.Superpower;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class SuperpowerController {

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
    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();

    @GetMapping("superpowers")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerDao.readAll();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("errors", violations);
        return "superpowers";
    }

    @PostMapping("addSuperpower")
    public String addSuperpower(String superpower, String description) {

        Superpower sp = new Superpower();
        sp.setSuperpower(superpower);
        sp.setDescription(description);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sp);

        if (violations.isEmpty()) {
            superpowerDao.create(sp);
        }
        return "redirect:/superpowers";
    }

    @GetMapping("delete")
    public String deleteSuperpower(Integer id) {
        superpowerDao.delete(id);
        return "redirect:/superpowers";

    }

    @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model) {
        Superpower sp = superpowerDao.readById(id);
        model.addAttribute("sp", sp);
        return "editSuperpower";
    }

    @PostMapping("editSuperpower")
    public String performEditSuperpower(Superpower superpower) {
        superpowerDao.update(superpower);
        return "redirect:/superpowers";
    }

}
