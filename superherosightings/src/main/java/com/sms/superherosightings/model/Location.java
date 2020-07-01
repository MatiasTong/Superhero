/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Shirley Sosa, Sangay Yolmo, Matias Tong 
 */
public class Location {

  private int locationId ; 
  private String name ; 
  private String description ; 
  private String address ; 
  private String city;
  private String state;
  private int zip; 
  private double latitude; 
  private double longitude; 


    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        this.LocationId = LocationId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = Description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = State;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int zip) {
        this.Zip = Zip;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.locationId;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.state);
        hash = 89 * hash + this.zip;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (this.zip != other.zip) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        return true;
    }

    

    
  
  
  

}
