/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.model;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Shirley Sosa, Sangay Yolmo, Matias Tong 
 */
public class Hero {
 
   private int heroId;
   
   @NotBlank(message = "Name must not be empty.")
   @Size(max = 30, message = "Name must be less than 30 characters.")
   private String name;
   
   @NotBlank(message = "Name must not be empty.")
   @Size(max = 30, message = "Name must be less than 30 characters.")
   private String description; 
   
   private Superpower superpower;
   
   @NotBlank(message = "Name must not be empty.")
   @Size(max = 30, message = "Name must be less than 30 characters.")
   private String type;

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
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

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hero{" + "heroId=" + heroId + ", name=" + name + ", description=" + description + ", superpower=" + superpower + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.heroId;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.description);
        hash = 53 * hash + Objects.hashCode(this.superpower);
        hash = 53 * hash + Objects.hashCode(this.type);
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
        final Hero other = (Hero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        return true;
    }

   
}
