CREATE TABLE Login
(
    login_id INT NOT NULL
    ,pass VARCHAR(20)
    ,PRIMARY KEY(login_id)
);

CREATE TABLE Groupa
(
    group_id INT NOT NULL
    ,names VARCHAR(20)
    ,headliner CHAR(1)
    ,PRIMARY KEY(group_id)
);

CREATE TABLE Venue
(
    venue_id INT NOT NULL
    ,venue_name VARCHAR(20)
    ,venue_type VARCHAR(20)
    ,venue_capacity INT
    ,PRIMARY KEY(venue_id)
);

CREATE TABLE City
(
    venue_id INT NOT NULL
    ,city_id INT NOT NULL
    ,city_name VARCHAR(20)
    ,PRIMARY KEY(city_id)
    ,FOREIGN KEY(venue_id) REFERENCES Venue(venue_id)
);

CREATE TABLE Country
(   
    city_id INT NOT NULL
    ,country_id INT NOT NULL
    ,country_name VARCHAR(30)
    ,PRIMARY KEY(country_id)
    ,FOREIGN KEY(city_id) REFERENCES City(city_id)
);

CREATE TABLE Tour
(
    tour_id INT NOT NULL
    ,tour_name VARCHAR(12)
    ,PRIMARY KEY(tour_id)
);

CREATE TABLE Concert
(
    concert_id INT NOT NULL
    ,country_id INT NOT null
    ,group_id INT NOT NULL
    ,tour_id INT NOT NULL
    ,country_name VARCHAR(50)
    ,date date
    ,city VARCHAR(100)
    ,PRIMARY KEY(concert_id)
    ,FOREIGN KEY(tour_id) REFERENCES Tour(tour_id)
    ,FOREIGN KEY(country_id) REFERENCES Country(country_id)
    ,FOREIGN KEY(group_id) REFERENCES Groupa(group_id)
    
);

CREATE TABLE Ticket
(
    concert_id INT NOT NULL
   	,ticket_id INT NOT NULL
    ,price INT
    ,buy_link VARCHAR(12)
    ,place_type VARCHAR(12)
    ,PRIMARY KEY (ticket_id)
    ,FOREIGN KEY(concert_id) REFERENCES Concert(concert_id)
);

CREATE TABLE Track
(
    group_id INT NOT NULL
    ,track_id INT NOT NULL
    ,track_name VARCHAR(12)
    ,album_name VARCHAR(20)
    ,PRIMARY KEY(track_id)
    ,FOREIGN KEY(group_id) REFERENCES Groupa(group_id)
);

CREATE TABLE Genre
(       
    group_id INT NOT NULL
    ,genre_id INT NOT NULL
    ,genre_name VARCHAR(12)
    ,PRIMARY KEY(genre_id)
    ,FOREIGN KEY(group_id) REFERENCES Groupa(group_id)
);

