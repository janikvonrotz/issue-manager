package ch.issueman.common.webservice;

import ch.issueman.common.Employee;
import ch.issueman.common.Person;
 
public class App {
 
    public static void main(String[] args) {
         
    	PersonController personc = new PersonController();
    	UserController userc = new UserController();
    	ProjectController projectc = new ProjectController();
    	EmployeeController employeec = new EmployeeController();

        Employee e = new Employee();
		e.setCompany("Redhat");
		Person p = new Person();
		p.setName("Test");
		e.setPerson(p);
		
		employeec.persist(e);
        
        System.exit(0);
    }
 
}