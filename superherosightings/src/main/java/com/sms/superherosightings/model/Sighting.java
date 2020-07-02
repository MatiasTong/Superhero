package com.sms.superherosightings.model;


import com.sms.superherosightings.model.Hero;
import com.sms.superherosightings.model.Location;
import java.time.LocalDateTime;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matiastong
 */
public class Sighting {
    private int sightingId;
    private LocalDateTime dateTime;
    private Hero hero;
    private Location location;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Sighting{" + "sightingId=" + sightingId + ", dateTime=" + dateTime + ", hero=" + hero + ", location=" + location + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.sightingId;
        hash = 97 * hash + Objects.hashCode(this.dateTime);
        hash = 97 * hash + Objects.hashCode(this.hero);
        hash = 97 * hash + Objects.hashCode(this.location);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

  
   
}
