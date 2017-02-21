DROP TABLE IF EXISTS Pferd;
DROP TABLE IF EXISTS Jockey;
DROP TABLE IF EXISTS Rennergebnis;

CREATE TABLE Pferd
    (
    chip_nr int NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    rasse varchar(255) NOT NULL,
    alter_jahre int NOT NULL,
    bild varchar(255) NOT NULL,
    min_gesw double NOT NULL CHECK(40 <= min_gesw AND min_gesw <= 60),
    max_gesw double NOT NULL CHECK(40 <= max_gesw AND max_gesw <= 60),
    deleted boolean NOT NULL,
    PRIMARY KEY(chip_nr)
    );

ALTER TABLE Pferd ADD CONSTRAINT gesw_constraint
    CHECK(min_gesw <= max_gesw);

CREATE TABLE Jockey
    (
    svnr int NOT NULL UNIQUE,
    können double NOT NULL,
    name varchar(255) NOT NULL,
    geburtsdatum DATE NOT NULL,
    gewicht int NOT NULL,
    deleted boolean NOT NULL,
    PRIMARY KEY(svnr)
    );

CREATE TABLE Rennergebnis
    (
    renn_id int NOT NULL,
    chip_nr int NOT NULL,
    svnr int NOT NULL,
    dgeschw double NOT NULL,
    pgeschw double NOT NULL,
    glueck double NOT NULL,
    koennen_b double NOT NULL,
    platz int NOT NULL,
    PRIMARY KEY(renn_id, chip_nr, svnr)
    );

ALTER TABLE Rennergebnis ADD FOREIGN KEY (chip_nr) REFERENCES Pferd(chip_nr);
ALTER TABLE Rennergebnis ADD FOREIGN KEY (svnr) REFERENCES Jockey(svnr);
ALTER TABLE Rennergebnis
    ADD CONSTRAINT unique_pferd_rennen UNIQUE(renn_id,chip_nr);
ALTER TABLE Rennergebnis
    ADD CONSTRAINT unique_jockey_rennen UNIQUE(renn_id,svnr);

INSERT INTO "PUBLIC"."PFERD" VALUES(0,'Philidor','Trakhner',22,'philidor_1.jpg',53.0,60.0,false);
INSERT INTO "PUBLIC"."PFERD" VALUES(1,'Rusty','Shetland Pony',16,'rusty_0.jpg',40.0,46.0,false);
INSERT INTO "PUBLIC"."PFERD" VALUES(2,'Kori','Huzule',13,'kori_1.jpg',40.0,51.0,false);
INSERT INTO "PUBLIC"."PFERD" VALUES(3,'Jaris','Fjordpony',9,'jaris_2.jpg',56.0,59.0,false);
INSERT INTO "PUBLIC"."PFERD" VALUES(4,'Pia','Haflinger',17,'pia_0.jpg',43.0,55.0,false);
INSERT INTO "PUBLIC"."PFERD" VALUES(5,'Benji','Haflinger-Noriker',15,'benji_1.jpg',40.0,44.0,false);
INSERT INTO "PUBLIC"."PFERD" VALUES(6,'Szilja','Araber',12,'szilja_0.jpg',48.0,57.0,false);


INSERT INTO "PUBLIC"."JOCKEY" VALUES(0,58.0,'Pinkie Pie','2004-08-12',47,false);
INSERT INTO "PUBLIC"."JOCKEY" VALUES(1,29.0,'Twilight Sparkle','2003-06-07',50,false);
INSERT INTO "PUBLIC"."JOCKEY" VALUES(2,301.0,'Rainbow Dash','2003-05-07',40,false);
INSERT INTO "PUBLIC"."JOCKEY" VALUES(3,30.0,'Fluttershy','2004-01-04',39,false);
INSERT INTO "PUBLIC"."JOCKEY" VALUES(4,48.0,'Rarity','2003-10-11',39,false);
INSERT INTO "PUBLIC"."JOCKEY" VALUES(5,287.0,'Princess Celestia','1987-09-08',61,false);
INSERT INTO "PUBLIC"."JOCKEY" VALUES(6,201.0,'Princess Luna','1990-01-04',59,false);


INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(0,1,1,47.257,45.2,0.98,1.0668,2);
INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(0,5,0,42.381,40.8,0.97,1.07,3);
INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(0,0,6,63.663,58.7,1.01,1.073,1);
INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(1,4,2,58.584,53.9,1.012,1.074,3);
INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(1,3,5,64.424,58.8,1.02,1.074,1);
INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(1,1,1,49.075,46.0,1.0,1.066,4);
INSERT INTO "PUBLIC"."RENNERGEBNIS" VALUES(1,6,4,64.042,57.0,1.05,1.07,2);
