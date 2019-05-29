package pl.kwmm.wis.web.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint;
import pl.kwmm.wis.model.Employee;

@Named
@SessionScoped
public class EmployeeController implements Serializable {

    public EmployeeController() {
    }

    @Inject
    EmployeeEndpoint employeeEndpoint;

    private Employee registeredEmployee;

    public Employee getRegisteredEmployee() {
        return registeredEmployee;
    }

    public void setRegisteredEmployee(Employee registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }

    public void registerEmployee() {
        employeeEndpoint.registerUserAccount(registeredEmployee);
        registeredEmployee = null;
    }

    public void registerEscalatedEmployee() {
        employeeEndpoint.registerEscalatedAccount(registeredEmployee);
        registeredEmployee = null;
    }

    public List<Employee> getAllEmployee() {
        return employeeEndpoint.getAllEmployee();
    }

    public void deleteEmployee(Employee e) {
        employeeEndpoint.deleteEmployee(e);
    }

    public void enableEmployee(Employee e) {
        employeeEndpoint.enableEmployee(e);
    }

    public void disableEmployee(Employee e) {
        employeeEndpoint.disableEmployee(e);
    }

    public Employee getCurrentAccount() {
        return employeeEndpoint.getCurrentAccount();
    }

    public void changeMyPassword(String password) {
        employeeEndpoint.changeMyPassword(password);
    }

    public void setImpTeamRole(Employee e) {
        employeeEndpoint.setImpTeamRole(e);
    }
    
    public void setAdminRole(Employee e) {
        employeeEndpoint.setAdminRole(e);
    }
    
    public void setEmployeeRole(Employee e) {
        employeeEndpoint.setEmployeeRole(e);
    }
    
    public void resetPassword(Employee e){
        employeeEndpoint.resetPassword(e);
    }

    public String saveEmployeeAfterEdit(Employee employee) {
        employeeEndpoint.saveEmployeeAfterEdit(employee);
        return "homePage";
    }

}
