DROP DATABASE IF EXISTS SuperHeroSightingsTest;
CREATE DATABASE SuperHeroSightingsTest;
USE SuperHeroSightingsTest;

CREATE TABLE Hero(
	HeroId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` VARCHAR(30) NOT NULL,
    Specialty VARCHAR(30) NOT NULL,
    `Type` VARCHAR(30) NOT NULL
);

CREATE TABLE Location(
	LocationId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` VARCHAR(30) NOT NULL,
	Address VARCHAR(30) NOT NULL,
    City VARCHAR(30) NOT NULL,
    State CHAR(2) NOT NULL,
    ZipCode CHAR(5) NOT NULL,
    Lat VARCHAR(10) NOT NULL,
    `Long` VARCHAR(10) NOT NULL
);

CREATE TABLE Organization(
	OrganizationId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` VARCHAR(30) NOT NULL,
     LocationId INT NOT NULL,
     FOREIGN KEY FK_Organization_Location_LocationId(LocationId) references Location(LocationId),
     Email VARCHAR(30) NOT NULL,
    `Type` VARCHAR(30) NOT NULL
);

CREATE TABLE HeroOrganization(
	HeroId INT,
    OrganizationId INT,
    PRIMARY KEY(HeroId, OrganizationId),
    FOREIGN KEY FK_HeroOrganization_Hero_HeroId(HeroId) references Hero(HeroId),
	FOREIGN KEY FK_HeroOrganization_Organization_OrganizationId(OrganizationId) references `Organization`(OrganizationId)
);

CREATE TABLE Sighting(
	SightingId INT PRIMARY KEY AUTO_INCREMENT,
    `Date` DATETIME,
    LocationId INT,
    HeroId INT,
    FOREIGN KEY FK_Sighting_Location_LocationId(LocationId) references Location(LocationId),
    FOREIGN KEY FK_Sighting5_Hero_HeroId(HeroId) references Hero(HeroId)
);