/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.model;

import java.math.BigDecimal;

/**
 *
 * @author matiastong
 */
public class Location {
  private int LocationId ; 
  private String Name ; 
  private String Description ; 
  private String Address ; 
  private String City;
  private String State;
  private int Zip; 
  private BigDecimal Latitude; 
  private BigDecimal Longitude; 

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

    public BigDecimal getLatitude() {
        return Latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.Latitude = Latitude;
    }

    public BigDecimal getLongitude() {
        return Longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.Longitude = Longitude;
    }
  
  
  
  

}
