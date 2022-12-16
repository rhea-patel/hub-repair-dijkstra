use riyap;
/*For storing poastalcodes, area and Population*/
create table Postalcode
 (
    Postalcodes varchar(6) primary key not null unique,
    population int,
    area int 
);
/*For storing Distribution HUb and location in coordinate form and link it with postal codes with one to many replationship with hubIdentifier*/
create table DistributionHub
(
HubIdentifier varchar(10) primary key not null unique,
Hub_location int,
Postalcodes varchar(6),
FOREIGN KEY (Postalcodes) 
REFERENCES Postalcode (Postalcodes)
);
/*For storing damaged hub and repair time & link it with HubIdentifier with one to one replationship with damagedhub*/
create table hubdamage
(
damagedhub varchar(10) primary key not null unique,
repairtime time,
HubIdentifier varchar(10),
FOREIGN KEY (HubIdentifier) 
REFERENCES DistributionHub (HubIdentifier)
);
/*For storing employeeid & link it with damagedhub with one to one replationship with employeeid*/
create table hubrepair
(
Employeeid int primary key not null unique,
damagedhub varchar(10),
Foreign key (damagedhub) 
references hubdamage (damagedhub)
);

    




