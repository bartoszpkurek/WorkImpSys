package pl.kwmm.wis.web.controller;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint;
import pl.kwmm.wis.model.Employee;

@SessionScoped
public class EmployeeController implements Serializable{
    
    @EJB 
    EmployeeEndpoint employeeEndpoint;
    
    private Employee registeredEmployee;

    public Employee getRegisteredEmployee() {
        return registeredEmployee;
    }

    public void setRegisteredEmployee(Employee registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }

    public void registerEmployee(){
        employeeEndpoint.registerUserAccount(registeredEmployee);
        registeredEmployee = null;
    }
    
    public void registerEscalatedEmployee(){
        employeeEndpoint.registerEscalatedAccount(registeredEmployee);
        registeredEmployee = null;
    }
    
    public List<Employee> getAllEmployee(){
        return employeeEndpoint.getAllEmployee();
    }
           
}
