/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.model;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Shirley Sosa, Sangay Yolmo, Matias Tong
 */
public class Organization {

    private int organizationId;
    
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 30, message = "Name must be less than 30 characters.")
    private String name;
    
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 30, message = "Description must be less than 30 characters.")
    private String description;
    private Location location;
    @Email
    private String email;
    private List<Hero> Heroes;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Hero> getHeroes() {
        return Heroes;
    }

    public void setHeroes(List<Hero> Heroes) {
        this.Heroes = Heroes;
    }

    @Override
    public String toString() {
        return "Organization{" + "organizationId=" + organizationId + ", name=" + name + ", description=" + description + ", location=" + location + ", email=" + email + ", Heroes=" + Heroes + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.organizationId;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.location);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.Heroes);
        hash = 47 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.Heroes, other.Heroes)) {
            return false;
        }
        return true;
    }

}
