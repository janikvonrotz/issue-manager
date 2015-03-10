create table employee(
    id int NOT NULL, PRIMARY KEY (id),
    company varchar(100) NOT NULL,
    
    CONSTRAINT `fkemployee` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON DELETE CASCADE
);