CREATE TABLE localization (
	id SERIAL PRIMARY KEY,
	cemetery VARCHAR(200),
	quarter VARCHAR(4) NOT NULL,
	localization_row VARCHAR(4) NOT NULL,
	localization_column VARCHAR(4) NOT NULL
);

CREATE TABLE grave (
	id SERIAL PRIMARY KEY,
	type VARCHAR(20),
	localization INT NOT NULL,
	grave_owner INT,
	photo VARCHAR(200)
);

CREATE TABLE grave_owner (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    pesel VARCHAR(50),
    street VARCHAR(100),
    parcel VARCHAR(10),
    city VARCHAR(50),
    postal_code VARCHAR(10),
    country VARCHAR(50),
    phone_number VARCHAR(50)
);

CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    password VARCHAR(70) NOT NULL,
    e_mail VARCHAR(70) NOT NULL,
    role  VARCHAR(50) NOT NULL,
    grave_owner INT,
    avatar VARCHAR(200)
);

CREATE TABLE deceased (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	birth_date DATE,
	death_date DATE,
	is_infectious_disease boolean,
	grave INT NOT NULL
);

ALTER TABLE grave ADD CONSTRAINT grave_localization
    FOREIGN KEY (localization)
    REFERENCES localization (id)
    ON DELETE CASCADE
;

ALTER TABLE grave ADD CONSTRAINT grave_grave_owner_
    FOREIGN KEY (grave_owner)
    REFERENCES grave_owner (id)
    ON DELETE CASCADE
;

ALTER TABLE app_user ADD CONSTRAINT app_user_grave_owner
    FOREIGN KEY (grave_owner)
    REFERENCES grave_owner (id)
    ON DELETE CASCADE
;

ALTER TABLE deceased ADD CONSTRAINT deceased_grave
    FOREIGN KEY (grave)
    REFERENCES grave (id)  
    ON DELETE CASCADE
;
