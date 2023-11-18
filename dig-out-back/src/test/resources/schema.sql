CREATE TABLE localization (
	id SERIAL PRIMARY KEY,
	cemetery VARCHAR(200),
	quarter VARCHAR(4) NOT NULL,
	localization_row VARCHAR(4) NOT NULL,
	localization_column VARCHAR(4) NOT NULL
);

CREATE TABLE grave (
	id SERIAL PRIMARY KEY,
	type VARCHAR (20),
	localization INT NOT NULL,
	grave_owner VARCHAR(50),
	availability boolean
);

ALTER TABLE grave ADD CONSTRAINT grave_localization
    FOREIGN KEY (localization)
    REFERENCES localization (id)  
    ON DELETE CASCADE
;

CREATE TABLE deceased (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	birth_date DATE,
	death_date DATE,
	is_infectious_disease boolean,
	grave INT NOT NULL
);

ALTER TABLE deceased ADD CONSTRAINT deceased_grave
    FOREIGN KEY (grave)
    REFERENCES grave (id)  
    ON DELETE CASCADE
;
