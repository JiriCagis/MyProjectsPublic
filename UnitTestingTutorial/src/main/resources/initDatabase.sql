drop all objects;

CREATE TABLE Label(
id int primary key auto_increment,
name varchar(100)
);

CREATE TABLE Artist(
id int primary key auto_increment,
name varchar(100)
);

CREATE TABLE Genre(
id int primary key auto_increment,
name varchar(100)
);

CREATE TABLE Reviewer(
id int primary key auto_increment,
name varchar(100)
);

CREATE TABLE Recording(
id int primary key auto_increment,
title varchar(100),
releaseDate DATE,
artistId int,
labelId int,
FOREIGN KEY (artistId) REFERENCES Artist(id),
FOREIGN KEY (labelId) REFERENCES Label(id)
);

CREATE TABLE Review(
id int primary key auto_increment,
reviewerId int,
recordingId int,
rating varchar(100),
review varchar(100),
FOREIGN KEY (reviewerId) REFERENCES Reviewer(id),
FOREIGN KEY (recordingId) REFERENCES Recording(id)
);

CREATE TABLE Track(
id int primary key auto_increment,
title varchar(100),
duration int,
genreId int,
artistId int,
recordingId int,
FOREIGN KEY (genreId) REFERENCES Genre(id),
FOREIGN KEY (artistId) REFERENCES Artist(id),
FOREIGN KEY (recordingId) REFERENCES Recording(id)
)



