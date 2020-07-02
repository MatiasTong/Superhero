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

    Lat float NOT NULL,
    `Long` float NOT NULL


);

CREATE TABLE Organization(
	OrganizationId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` VARCHAR(30) NOT NULL,
     LocationId INT NOT NULL,
     FOREIGN KEY fk_Organization_Location_LocationId(LocationId) references Location(LocationId),
     Email VARCHAR(30) NOT NULL,
    `Type` VARCHAR(30) NOT NULL
);

CREATE TABLE HeroOrganization(
	HeroId INT,
    OrganizationId INT,
    PRIMARY KEY(HeroId, OrganizationId),
    FOREIGN KEY fk_HeroOrganization_Hero(HeroId) references Hero(HeroId),
	FOREIGN KEY fk_HeroOrganization_Organization(OrganizationId) references Organization(OrganizationId)
);

CREATE TABLE Sighting(
	SightingId INT PRIMARY KEY AUTO_INCREMENT,
    `Date` DATETIME,
    LocationId INT,
    HeroId INT,
    FOREIGN KEY fk_Sighting_Location(LocationId) references Location(LocationId),
    FOREIGN KEY fk_Sighting5_Hero(HeroId) references Hero(HeroId)
);

