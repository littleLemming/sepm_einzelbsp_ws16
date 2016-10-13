!!!!unique kombi von rennen-nummer & jockey-svnr bzw rennen-nummer & pferde-chipnummer!!!!

CREATE TABLE Pferd
    (
    chip_nr int NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    bild varchar(255) NOT NULL,
    del BOOLEAN NOT NULL,
    min_gesw int NOT NULL CHECK(40 <= min_gesw AND min_gesw <= 60),
    max_gesw int NOT NULL CHECK(40 <= max_gesw AND max_gesw <= 60),
    PRIMARY KEY(chip_nr)
    );

ALTER TABLE Pferd ADD CONSTRAINT gesw_constraint
CHECK (min_gesw <= max_gesw)

CREATE TABLE Jockey
    (
    svnr int NOT NULL UNIQUE,
    können int NOT NULL,
    name varchar(255) NOT NULL,
    del BOOLEAN NOT NULL,
    PRIMARY KEY(svnr)
    );

CREATE TABLE Rennergebnis
    (
    renn_id int NOT NULL,
    chip_nr int NOT NULL FOREIGN KEY REFERENCES Pferd(chip_nr),
    svnr int NOT NULL FOREIGN KEY REFERENCES Jockey(svnr),
    geschw double NOT NULL,
    platz int NOT NULL,
    PRIMARY KEY(renn_id, chip_nr, renn_id)
    );