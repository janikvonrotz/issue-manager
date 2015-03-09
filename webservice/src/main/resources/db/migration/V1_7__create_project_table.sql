create table project(
    id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
    title varchar(100) NOT NULL,
    employee_id int NOT NULL,
    
    FOREIGN KEY (employee_id)
      REFERENCES employee(id)
);