package pl.kwmm.wis.web.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint;
import pl.kwmm.wis.model.Employee;

/**
 * Named Bean working as controller for Employee related data. 
 * Gathers all methods in one place for Employee management and pass the methods/data to Employee Endpoint for business logic.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Named
@SessionScoped
public class EmployeeController implements Serializable {

//    To be deleted if not needed    
//    public EmployeeController() {
//    }
    
    /**
     * Endpoint Injection for Employee data
     */
    @Inject
    private EmployeeEndpoint employeeEndpoint;
    
    //*****************Fields
    private Employee registeredEmployee;
    
    //*****************Methods
    /**
     * Method called from AddEmployeeConfirmBean to register Employee employee with normal privileges.
     * At the last step sets AddEmployeeConfirmBean.registeredEmployee field to null to prevent registering same account.
     * 
     * @see pl.kwmm.wis.web.bean.employee.AddEmployeeConfirmBean#register() 
     */
    public void registerEmployee() {
        employeeEndpoint.registerUserAccount(registeredEmployee);
        registeredEmployee = null;
    }
    
    /**
     * Method called from AddEmployeeConfirmBean to register Admin or ImpTeam employee with higher privileges.
     * At the last step sets AddEmployeeConfirmBean.registeredEmployee field to null to prevent registering same account.
     * 
     * @see pl.kwmm.wis.web.bean.employee.AddEmployeeConfirmBean#register() 
     */
    public void registerEscalatedEmployee() {
        employeeEndpoint.registerEscalatedAccount(registeredEmployee);
        registeredEmployee = null;
    }
    
    //*****************Standard Getters/Setters
    public Employee getRegisteredEmployee() {
        return registeredEmployee;
    }

    public void setRegisteredEmployee(Employee registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }
    
    //************************TODO
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
