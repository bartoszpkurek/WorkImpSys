package pl.kwmm.wis.ejb.endpoint;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.utils.EmployeeUtils;

@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EmployeeEndpoint extends AbstractEndpoint {

    @Inject
    EmployeeFacade employeefacade;


    public void registerUserAccount(Employee e) {
        employeefacade.create(e);
        e.setStatus(true);
        e.setNotificationpoints(0);
        employeefacade.edit(e);
    }

    public void registerEscalatedAccount(Employee e) {

        e.setStatus(false);
        e.setLasttype(e.getType());
        e.setType("Disabled");
        e.setNotificationpoints(0);
        employeefacade.create(e);
    }

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
        e.setType("Disabled");
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
        e.setType("ImpTeam");
        employeefacade.edit(e);
    }
    
    public void setAdminRole(Employee e){
        e.setLasttype(e.getType());
        e.setType("Admin");
        employeefacade.edit(e);
    }
    
    public void setEmployeeRole(Employee e){
        e.setLasttype(e.getType());
        e.setType("Employee");
        employeefacade.edit(e);
    }
    
    public void resetPassword(Employee e){
        EmployeeUtils.passwordChange(e);
        employeefacade.edit(e);
    
    }
    

    

}
