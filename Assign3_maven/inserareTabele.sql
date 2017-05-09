USE cabinet;
ALTER TABLE consultation
    drop FOREIGN KEY consultation_ibfk_1,
    drop FOREIGN KEY consultation_ibfk_2,
    DROP KEY patientPNC,
    DROP KEY employeePNC;
 
truncate patient;
truncate employee;
truncate consultation;


ALTER TABLE consultation
    add FOREIGN KEY(patientPNC) references patient(personalNumericalCode),
	add FOREIGN KEY(employeePNC) references employee(personalNumericalCode);
    
    
INSERT INTO patient(name,identityCardNumber,personalNumericalCode,birthDate,address)
VALUES
('Popescu Ion','123456','1930507891234','1993-05-07','Cluj-Napoca'),
('Miron Costin','456231','1780123412345','1978-01-23','Bistrita'),
('Mateescu Denisa','345326','2940618191234','1994-06-18','Nasaud'),
('Croitor Emilia','234156','2910517891234','1991-05-17','Cluj-Napoca');

INSERT INTO employee(name,identityCardNumber,personalNumericalCode,address,agency,role,passwordE)
VALUES
('Mihaiescu Ion','302456','1901007891234','Cluj-Napoca','BT Baritiu','user','123456'),
('Balota Ioana','000000','2951005060596','Bistrita','BT Baritiu','user','123456'),
('admin','000000','2951005060597','Bistrita','BT Baritiu','admin','000000'),
('Marinescu Ciprian','400623','1920211234121','Cluj-Napoca','BT Marasti','user','112233');

INSERT INTO consultation(employeePnc,patientPnc,dateH,duration,diagnostic,observations)
VALUES
('1901007891234','1930507891234','2017-05-04',15,'diagnostic','observatii'),
('2951005060596','2940618191234','2017-05-09',15,'diagnostic','observatii');

