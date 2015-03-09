create table employee(
    id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
    company varchar(100) NOT NULL,
    
    CONSTRAINT `fkemployee` FOREIGN KEY (`id`) REFERENCES `person` (`id`) 
);