INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('New moon cemetery', 'A1', '7', '18');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('Rest in peace cemetery', 'J8', 'C', '1');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('New moon cemetery', 'B4', '4', 'A');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('Oak valley cemetery', 'A1', '14', '21');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('Green hills cemetery', 'A0', '11', '3');


INSERT INTO grave (type, localization, grave_owner) VALUES ('coffin grave', 4, 'John Smith');
INSERT INTO grave (type, localization, grave_owner) VALUES ('coffin grave', 1, 'Edith Gawronsky');
INSERT INTO grave (type, localization, grave_owner) VALUES ('urn grave', 3, 'Mary Goldfin');
INSERT INTO grave (type, localization, grave_owner) VALUES ('columbarium', 5, 'Ann Black');
INSERT INTO grave (type, localization, grave_owner) VALUES ('urn grave', 2, 'Sean Williams');


INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Noah', 'Gross', '1974-11-01', '2023-06-13', false, 5);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Melody', 'Fletcher', '1942-07-29', '2004-01-16', false, 4);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Vera', 'Park', '1991-07-01', '1999-02-28', true, 2);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Adam', 'Norris', '1978-11-01', '2019-12-17', false, 1);
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES ('Collin', 'Moody', '1964-04-19', '2023-01-07', false, 3);
