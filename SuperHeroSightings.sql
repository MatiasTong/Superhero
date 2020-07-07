DROP DATABASE IF EXISTS SuperHeroSightings;
CREATE DATABASE SuperHeroSightings;
USE SuperHeroSightings;

CREATE TABLE Superpower(
    SuperpowerId INT PRIMARY KEY AUTO_INCREMENT,
    Power VARCHAR(30) NOT NULL,
    `Description` VARCHAR(60) NOT NULL
);

CREATE TABLE Hero(
	HeroId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,

    `Description` VARCHAR(30) NOT NULL,
    SuperpowerId INT NOT NULL,
    FOREIGN KEY fk_Hero_Superpower_SuperpowerId(SuperpowerId) references Superpower(SuperpowerId),

    `Type` VARCHAR(30) NOT NULL
);

CREATE TABLE Location(
	LocationId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` TEXT NOT NULL,
	Address VARCHAR(30) NOT NULL,
    City VARCHAR(30) NOT NULL,
    State CHAR(2) NOT NULL,
    ZipCode CHAR(5) NOT NULL,
    Lat decimal(8,6) NOT NULL,
    `Long` decimal(9,6) NOT NULL
);

CREATE TABLE Organization(
	OrganizationId INT PRIMARY KEY AUTO_INCREMENT,
    `Name` VARCHAR(30) NOT NULL,
    `Description` TEXT NOT NULL,
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
    Date DATETIME,
    LocationId INT,
    HeroId INT,
    FOREIGN KEY fk_Sighting_Location(LocationId) references Location(LocationId),
    FOREIGN KEY fk_Sighting5_Hero(HeroId) references Hero(HeroId)
);

insert into Superpower (Power,Description) values("strength", "test description");
select * from superpower;
insert into Hero (`Name`,`Description`,SuperpowerId,`Type`) values 
("batman", "lurks in the dark", 1, "hero"),
("superman", "flies in the light", 1, "hero");
select * from hero;
insert into Location(`Name`,`Description`,Address, City, State, ZipCode,
Lat, `Long`) values
("MacDonalds","fast food restaurant", "142-14 26th Ave.", "Flushing","ny", "11111", -30.000000,145.000000 ),
("Burger King","fast food restaurant", "142-14 26th Ave.", "Flushing","ny", "99999", -50.000000,155.000000 );

select * from location;

insert into Sighting(Date, LocationId, HeroId) values
("2020-07-30 07:33:20", 1, 1),
("2020-06-30 06:22:20", 2, 2); 

select * from sighting;









