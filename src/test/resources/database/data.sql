INSERT INTO localization (quarter, localization_row, localization_column) VALUES ('A1', '7', '18');
INSERT INTO localization (quarter, localization_row, localization_column) VALUES ('J8', 'C', '1');
INSERT INTO localization (quarter, localization_row, localization_column) VALUES ('B4', '4', 'A');
INSERT INTO localization (quarter, localization_row, localization_column) VALUES ('A1', '14', '21');
INSERT INTO localization (quarter, localization_row, localization_column) VALUES ('A0', '11', '3');


INSERT INTO grave (cemetery, type, localization, grave_owner, availability) VALUES ('New moon cemetery', 'coffin grave', 4, 'John Smith', true);
INSERT INTO grave (cemetery, type, localization, grave_owner, availability) VALUES ('Rest in peace cemetery', 'coffin grave', 1, 'Edith Gawronsky', false);
INSERT INTO grave (cemetery, type, localization, grave_owner, availability) VALUES ('New moon cemetery', 'urn grave', 3, 'Mary Goldfin', false);
INSERT INTO grave (cemetery, type, localization, grave_owner, availability) VALUES ('Oak valley cemetery', 'columbarium', 5, 'Ann Black', false);
INSERT INTO grave (cemetery, type, localization, grave_owner, availability) VALUES ('Green hills cemetery', 'urn grave', 2, 'Sean Williams', true);


INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Noah', 'Gross', '1974-11-01', '2023-06-13', false, 5);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Melody', 'Fletcher', '1942-07-29', '2004-01-16', false, 4);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Vera', 'Park', '1991-07-01', '1999-02-28', true, 2);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Adam', 'Norris', '1978-11-01', '2019-12-17', false, 1);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Collin', 'Moody', '1964-04-19', '2023-01-07', false, 3);
