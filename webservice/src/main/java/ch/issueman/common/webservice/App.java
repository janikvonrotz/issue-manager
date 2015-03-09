package ch.issueman.common.webservice;
 
public class App {
 
    public static void main(String[] args) {
         
    	PersonController personc = new PersonController();
    	UserController userc = new UserController();
    	ProjectController projectc = new ProjectController();
    	EmployeeController employeec = new EmployeeController();
                 
        System.out.println(personc.getById(1).getName());
        System.out.println(userc.getById(1).getPerson().getName());
        System.out.println(projectc.getById(1).getEmployee().getPerson().getName());
        System.out.println(employeec.getById(1).getPerson().getName());
       
        System.exit(0);
    }
 
}