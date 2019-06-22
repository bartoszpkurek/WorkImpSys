package pl.kwmm.wis.web.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint;
import pl.kwmm.wis.model.Employee;

/**
 * Named Bean working as controller for Employee related data. Gathers all
 * methods in one place for Employee management and pass the methods/data to
 * Employee Endpoint for business logic. Does not have logger Instance.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Named
@SessionScoped
public class EmployeeController implements Serializable {

    /**
     * Endpoint Injection for Employee data
     */
    @Inject
    private EmployeeEndpoint employeeEndpoint;

    //*****************Fields
    private Employee registeredEmployee;

    //*****************Methods
    /**
     * Method called from AddEmployeeConfirmBean to register Employee employee
     * with normal privileges. At the last step sets
     * AddEmployeeConfirmBean.registeredEmployee field to null to prevent
     * registering same account.
     *
     * @see pl.kwmm.wis.web.bean.employee.AddEmployeeConfirmBean#register()
     */
    public void registerEmployee() throws NoSuchAlgorithmException{
        employeeEndpoint.registerUserAccount(registeredEmployee);
        registeredEmployee = null;
    }

    /**
     * Method called from AddEmployeeConfirmBean to register Admin or ImpTeam
     * employee with higher privileges. At the last step sets
     * AddEmployeeConfirmBean.registeredEmployee field to null to prevent
     * registering same account.
     *
     * @see pl.kwmm.wis.web.bean.employee.AddEmployeeConfirmBean#register()
     */
    public void registerEscalatedEmployee() throws NoSuchAlgorithmException{
        employeeEndpoint.registerEscalatedAccount(registeredEmployee);
        registeredEmployee = null;
    }

    /**
     * Method for invoking enabling Employee through method of EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void enableEmployee(Employee e) {
        employeeEndpoint.enableEmployee(e);
    }

    /**
     * Method for invoking disabling Employee through method of
     * EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void disableEmployee(Employee e) {
        employeeEndpoint.disableEmployee(e);
    }

    /**
     * Method for invoking deleting Employee through method of EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void deleteEmployee(Employee e) {
        employeeEndpoint.deleteEmployee(e);
    }

    /**
     * Method for invoking changing role to Employee through method of
     * EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void setEmployeeRole(Employee e) {
        employeeEndpoint.setEmployeeRole(e);
    }

    /**
     * Method for invoking changing role to ImpTeam through method of
     * EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void setImpTeamRole(Employee e) {
        employeeEndpoint.setImpTeamRole(e);
    }

    /**
     * Method for invoking changing role to Admin through method of
     * EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void setAdminRole(Employee e) {
        employeeEndpoint.setAdminRole(e);
    }

    /**
     * Method for invoking saving edited Account from EmployeeEndpoint. Return
     * String for navigation rules.
     *
     * @param employee
     * @return String
     */
    public String changeMyData(Employee employee) {
        employeeEndpoint.changeMyData(employee);
        return "homePage";
    }

    /**
     * Method for invoking changing password from EmployeeEndpoint.
     *
     * @param password
     */
    public void changeMyPassword(String password) throws NoSuchAlgorithmException{
        employeeEndpoint.changeMyPassword(password);
    }

    /**
     * Method for invoking finding current logged account from EmployeeEndpoint.
     *
     * @return invoking method from Endpoint
     */
    public Employee getCurrentAccount() {
        return employeeEndpoint.getCurrentAccount();
    }

    /**
     * Method for invoking resetting password for account from EmployeeEndpoint.
     *
     * @param e Employee Object
     * @param tempPassword 
     * @throws NoSuchAlgorithmException
     */
    public void resetPassword(Employee e, String tempPassword)throws NoSuchAlgorithmException{
        employeeEndpoint.resetPassword(e, tempPassword);
    }

    /**
     * Method for listing all employees.
     *
     * @return List of Employees
     */
    public List<Employee> getAllEmployee() {
        return employeeEndpoint.getAllEmployee();
    }

    //*****************Standard Getters/Setters
    public Employee getRegisteredEmployee() {
        return registeredEmployee;
    }

    public void setRegisteredEmployee(Employee registeredEmployee) {
        this.registeredEmployee = registeredEmployee;
    }

}
