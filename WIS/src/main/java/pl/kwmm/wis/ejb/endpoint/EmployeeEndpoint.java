package pl.kwmm.wis.ejb.endpoint;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.ejb.interceptor.LoggingInterceptor;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.utils.EmployeeUtils;

/**
 * Stateful Endpoint for business logic and performing Entity JPA methods.
 * Important! Avoid multi-threading after Injection as it's state could be shared.
 * Interceptor is added for event logs.
 *
 * LocalBean for no-interface view.
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class EmployeeEndpoint extends AbstractEndpoint {

    /**
     * Facades Injection for Employee JPA data
     */
    @Inject
    private EmployeeFacade employeefacade;

    //*****************Methods
    /**
     * Method to pass register Employee into Database.
     * Before creating sets status to true and notification points to 0 for starter point.
     * Lastly calling method from EmployeeFacade to create JPA entry in database.
     * 
     * @see pl.kwmm.wis.web.controller.EmployeeController#registerEmployee() 
     * 
     * @param e is Employee instance
     */
    public void registerUserAccount(Employee e) {
        e.setStatus(true);
        e.setNotificationpoints(0);
        employeefacade.create(e);
    }

    /**
     * Method to pass register Admin/ImpTeam Employee into Database.
     * Before creating: 
     * sets status to false to prevent login, 
     * sets lasttype to type chosen on register form to automating admin enabling account, 
     * sets type for type from enum - DISABLED (same as one role for jdbc), 
     * sets notification points to 0 for starter point. 
     * Lastly calling method from EmployeeFacade to create JPA entry in database.
     *  
     * @see pl.kwmm.wis.web.controller.EmployeeController#registerEmployee() 
     * 
     * @param e is Employee instance
     */
    public void registerEscalatedAccount(Employee e) {
        e.setStatus(false);
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.Disabled);
        e.setNotificationpoints(0);
        employeefacade.create(e);
    }

    //************************TODO
    public List<Employee> getAllEmployee() {
        return employeefacade.findAll();
    }

    public void deleteEmployee(Employee e) {
        employeefacade.remove(e);
    }

    public void enableEmployee(Employee e) {
        e.setStatus(true);
        e.setType(e.getLasttype());
        e.setLasttype(null);
        employeefacade.edit(e);
    }

    public void disableEmployee(Employee e) {
        e.setStatus(false);
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.Disabled);
        employeefacade.edit(e);
    }

    public String getCurrentLogin() {
        return sessionContext.getCallerPrincipal().getName();
    }

    public Employee getCurrentAccount() {
        return findLogin(getCurrentLogin());
    }

    public Employee findLogin(String login) {
        return employeefacade.findLogin(login);
    }

    public void changeMyPassword(String password) {
        Employee employee = getCurrentAccount();
        employee.setPassword(password);
        employeefacade.edit(employee);
    }

  
    public void saveEmployeeAfterEdit(Employee employee) {
        if (null == employee) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        if (!employee.equals(employee)) {
            throw new IllegalArgumentException("Modyfikowane konto niezgodne z wczytanym");
        }

        EmployeeUtils.employeeEdit(employee, employee);
        employeefacade.edit(employee);
        employee = null;
    }
    
    public void setImpTeamRole(Employee e){
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.ImpTeam);
        employeefacade.edit(e);
    }
    
    public void setAdminRole(Employee e){
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.Admin);
        employeefacade.edit(e);
    }
    
    public void setEmployeeRole(Employee e){
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.Employee);
        employeefacade.edit(e);
    }
    
    public void resetPassword(Employee e){
        EmployeeUtils.passwordChange(e);
        employeefacade.edit(e);
    }
    

    

}
