create table project(
    id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
    title varchar(100) NOT NULL,
    employer_id int NOT NULL,
    
    FOREIGN KEY (employer_id)
      REFERENCES employer(id)
);