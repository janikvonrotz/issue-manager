create table user(
    id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(100) NOT NULL,
    
	CONSTRAINT `fkuser` FOREIGN KEY (`id`) REFERENCES `person` (`id`)  
);