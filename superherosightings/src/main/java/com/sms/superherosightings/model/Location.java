/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms.superherosightings.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Shirley Sosa, Sangay Yolmo, Matias Tong
 */
public class Location {

    private int locationId;

    @NotBlank(message = "Location Name must not be empty.")
    @Size(max = 30, message = "Name must be less than 30 characters.")
    private String name;

    @NotBlank(message = "Description must not be empty.")
    @Size(max = 50, message = "Description must be less than 50 characters.")
    private String description;

    @NotBlank(message = "Address must not be empty.")
    @Size(max = 30, message = "Address must be less than 30 characters.")
    private String address;

    @NotBlank(message = "City must not be empty.")
    @Size(max = 30, message = "City must be less than 30 characters.")
    private String city;

    @NotBlank(message = "State must not be empty.")
    @Size(max = 20, message = "State must be less than 20 characters.")
    private String state;

//     @Size(min=5,max = 5, message = "Zipcode must be 5 characters.")
    @NotBlank(message = "Zipcode must not be empty")
    @Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$", message = "Not a valid U.S. zip code")
    private String zip;
    
    @NotNull(message = "latitude must not be empty")
    @Digits(integer=2, fraction=6, message = "Enter latitude coordinates with up to 6 decimal places")
    @DecimalMin(value = "-90.000000", message = "Latitude is out of range. Must be between -90.000000 and 90.000000")
    @DecimalMax(value = "90.000000", message = "Latitude is out of range. Must be between -90.000000 and 90.000000")
    private Double latitude;
    
    @NotNull(message = "longitude must not be empty")
    @Digits(integer=3, fraction=6, message = "Enter longitide coordinates with up to 6 decimal places")
    @DecimalMin(value = "-180.000000", message = "Longitude is out of range. Must be between -180.000000 and 180.000000")
    @DecimalMax(value = "180.000000", message = "Longitude is out of range. Must be between -180.000000 and 180.000000")
    private Double longitude;
    
//     @NotBlank(message = "Is Blank")
//    @Digits(integer=3, fraction=8, message = "Is Digits")

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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

 

    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", name=" + name + ", description=" + description + ", address=" + address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.locationId;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + Objects.hashCode(this.city);
        hash = 37 * hash + Objects.hashCode(this.state);
        hash = 37 * hash + Objects.hashCode(this.zip);
        hash = 37 * hash + Objects.hashCode(this.latitude);
        hash = 37 * hash + Objects.hashCode(this.longitude);
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
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }


   
}
