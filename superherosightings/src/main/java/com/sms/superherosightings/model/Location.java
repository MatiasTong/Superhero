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
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;

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

    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;

    }

    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;

    }

    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;

    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
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

    
   


  

}
