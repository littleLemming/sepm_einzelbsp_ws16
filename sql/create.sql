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