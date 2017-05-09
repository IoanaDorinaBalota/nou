CREATE DATABASE IF NOT EXISTS cabinet;
USE cabinet;
drop table if exists consultation;
drop table if exists patient;
drop table if exists employee;



CREATE TABLE IF NOT EXISTS patient
(name char(30),
identityCardNumber char(6),
personalNumericalCode char(13) unique primary key,
birthDate datetime,
address char(50));

CREATE TABLE IF NOT EXISTS employee
( name char(30),
identityCardNumber char(6),
personalNumericalCode char(13) unique primary key,
address char(50),
agency char(50),
role char(50),
passwordE char(30));


CREATE TABLE IF NOT EXISTS consultation
(
id int unique auto_increment primary key,
employeePNC char(13),
patientPNC char(13),
dateH datetime,
duration int,
diagnostic char(200),
observations char(200),
FOREIGN KEY (employeePNC) REFERENCES employee(personalNumericalCode) ,
FOREIGN KEY (patientPNC) REFERENCES patient(personalNumericalCode));

