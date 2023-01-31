package sg.edu.nus.iss.day22.repo;

import java.util.List;

import sg.edu.nus.iss.day22.model.Employee;

public interface EmployeeRepo {
    
    List<Employee> retrieveEmployeeList();
    
}
