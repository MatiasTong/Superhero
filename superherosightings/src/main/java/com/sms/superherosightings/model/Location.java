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

    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", name=" + name + ", description=" + description + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    @Override
    public int hashCode() {


        int hash = 5;
        hash = 97 * hash + this.locationId;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + this.zip;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));


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
