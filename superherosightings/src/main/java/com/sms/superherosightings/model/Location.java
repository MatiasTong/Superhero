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
  private int LocationId ; 
  private String Name ; 
  private String Description ; 
  private String Address ; 
  private String City;
  private String State;
  private int Zip; 
  private double Latitude; 
  private double Longitude; 

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int LocationId) {
        this.LocationId = LocationId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int Zip) {
        this.Zip = Zip;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double Longitude) {
        this.Longitude = Longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.LocationId;
        hash = 53 * hash + Objects.hashCode(this.Name);
        hash = 53 * hash + Objects.hashCode(this.Description);
        hash = 53 * hash + Objects.hashCode(this.Address);
        hash = 53 * hash + Objects.hashCode(this.City);
        hash = 53 * hash + Objects.hashCode(this.State);
        hash = 53 * hash + this.Zip;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.Latitude) ^ (Double.doubleToLongBits(this.Latitude) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.Longitude) ^ (Double.doubleToLongBits(this.Longitude) >>> 32));
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
        if (this.LocationId != other.LocationId) {
            return false;
        }
        if (this.Zip != other.Zip) {
            return false;
        }
        if (Double.doubleToLongBits(this.Latitude) != Double.doubleToLongBits(other.Latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.Longitude) != Double.doubleToLongBits(other.Longitude)) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Address, other.Address)) {
            return false;
        }
        if (!Objects.equals(this.City, other.City)) {
            return false;
        }
        if (!Objects.equals(this.State, other.State)) {
            return false;
        }
        return true;
    }

  
  
  

}
