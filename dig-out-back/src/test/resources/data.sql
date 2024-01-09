INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('New moon cemetery', 'A1', '7', '18');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('Rest in peace cemetery', 'J8', 'C', '1');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('New moon cemetery', 'B4', '4', 'A');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('Oak valley cemetery', 'A1', '14', '21');
INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES ('Green hills cemetery', 'A0', '11', '3');

INSERT INTO grave_owner (first_name, last_name, pesel, street, parcel, city, postal_code, country, phone_number) VALUES ('Emily', 'Blunt', '88121417864', '5th Avenue', '18', 'Brigthtown', '47-427', 'Great Britain', '459-782-145');
INSERT INTO grave_owner (first_name, last_name, pesel, street, parcel, city, postal_code, country, phone_number) VALUES ('Amanda', 'Mallow', '74081517695', 'Light Street', '14C/7', 'Evenstone', '25-486', 'Great Britain', NULL);
INSERT INTO grave_owner (first_name, last_name, pesel, street, parcel, city, postal_code, country, phone_number) VALUES ('Robert', 'Watson', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO grave_owner (first_name, last_name, pesel, street, parcel, city, postal_code, country, phone_number) VALUES ('Susan', 'Austin', '88121417864', 'Viersene Strasse', '24A', 'Viersen', '12-784', 'Germany', '574-445-127');
INSERT INTO grave_owner (first_name, last_name, pesel, street, parcel, city, postal_code, country, phone_number) VALUES ('John', 'Gross', NULL, 'Niepodleglosci', '14B/1', 'Wielowies', '24-576', 'Poland', '697-485-127');

INSERT INTO grave (type, localization, grave_owner, photo, is_place_available) VALUES ('coffin grave', 4, 2, '123452023011812345', true);
INSERT INTO grave (type, localization, grave_owner, photo, is_place_available) VALUES ('coffin grave', 1, 3, '123452023072512345', true);
INSERT INTO grave (type, localization, grave_owner, photo, is_place_available) VALUES ('urn grave', 3, 1, '123452020011812345', true);
INSERT INTO grave (type, localization, grave_owner, photo, is_place_available) VALUES ('columbarium', 5, 5, NULL, true);
INSERT INTO grave (type, localization, grave_owner, photo, is_place_available) VALUES ('urn grave', 2, 4, '123452019110112345', true);

INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave, photo) VALUES ('Noah', 'Gross', '1974-11-01', '2023-06-13', false, 5, '1236364364512345');
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave, photo) VALUES ('Melody', 'Fletcher', '1942-07-29', '2004-01-16', false, 4, '1234564564512345');
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave, photo) VALUES ('Vera', 'Park', '1991-07-01', '1999-02-28', true, 2, '1234564643512345');
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave, photo) VALUES ('Adam', 'Norris', '1978-11-01', '2019-12-17', false, 1, '123457656542512345');
INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave, photo) VALUES ('Collin', 'Moody', '1964-04-19', '2023-01-07', false, 3, '1234564565412345');

INSERT INTO app_user (nickname, password, e_mail, role, grave_owner, photo) VALUES ('dbesciak', 'aloha', 'dbesciak@gmail.com', 'USER', 4, NULL);
INSERT INTO app_user (nickname, password, e_mail, role, grave_owner, photo) VALUES ('administrator', 'difficultPassword', 'administrator@dig-out.com', 'ADMIN', NULL, '123452011142512345');
INSERT INTO app_user (nickname, password, e_mail, role, grave_owner, photo) VALUES ('blondie85', 'myNewPassword', 'blondie85@onet.com', 'USER', 1, '1234521178212345');
INSERT INTO app_user (nickname, password, e_mail, role, grave_owner, photo) VALUES ('johnGross', 'littleJohnny', 'john_gross@gmail.com', 'USER', 5, '123452174612345');
INSERT INTO app_user (nickname, password, e_mail, role, grave_owner, photo) VALUES ('digger', 'just_be_nice', 'digger2023@yahoo.com', 'USER', NULL, NULL);
