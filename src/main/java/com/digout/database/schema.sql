CREATE TABLE localization (
	id INT NOT NULL,
	quarter VARCHAR(4) NOT NULL,
	localization_row VARCHAR(4) NOT NULL,
	localization_column VARCHAR(4) NOT NULL,
	CONSTRAINT localization_pk PRIMARY KEY (id)
);

CREATE TABLE grave (
	id INT NOT NULL,
	cemetery VARCHAR(20),
	type VARCHAR (20),
	localization INT NOT NULL,
	grave_owner VARCHAR(50),
	availability boolean,
	CONSTRAINT grave_pk PRIMARY KEY (id)
);

ALTER TABLE grave ADD CONSTRAINT grave_localization
    FOREIGN KEY (localization)
    REFERENCES localization (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

CREATE TABLE deceased (
	id INT NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	birth_date timestamp,
	death_date timestamp,
	is_infectious_disease boolean,
	grave INT NOT NULL,
	CONSTRAINT deceased_pk PRIMARY KEY (id)
);

ALTER TABLE deceased ADD CONSTRAINT deceased_grave
    FOREIGN KEY (grave)
    REFERENCES grave (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;
