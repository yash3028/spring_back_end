-- liquibase formatted sql

-- changeset HP:1748875191661-1 splitStatements:false
-- precondition-sql-check expectedresult:0 select count(*) from information_schema.tables where table_name='bus_details'
CREATE TABLE bus_details (id BIGINT AUTO_INCREMENT NOT NULL, operator_name VARCHAR(255) NULL, bus_no VARCHAR(255) NULL, `From` VARCHAR(255) NULL, `To` VARCHAR(255) NULL, departure_date VARCHAR(255) NULL, departure_time VARCHAR(255) NULL, arrival_time VARCHAR(255) NULL, seats_available VARCHAR(255) NULL, price DOUBLE NULL, CONSTRAINT PK_BUS_DETAILS PRIMARY KEY (id));

-- changeset HP:1748875191661-2 splitStatements:false
-- precondition-sql-check expectedresult:0 select count(*) from information_schema.tables where table_name='user'
CREATE TABLE `user` (id INT AUTO_INCREMENT NOT NULL, fullName VARCHAR(255) NULL, email VARCHAR(255) NULL, mobile VARCHAR(255) NULL, userrole VARCHAR(255) NULL, companyName VARCHAR(255) NULL, date_of_birth date NULL, password VARCHAR(255) NULL, token VARCHAR(255) NULL, CONSTRAINT PK_USER PRIMARY KEY (id), UNIQUE (mobile));

