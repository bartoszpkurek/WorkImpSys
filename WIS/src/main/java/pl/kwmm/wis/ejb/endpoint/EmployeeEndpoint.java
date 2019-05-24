package pl.kwmm.wis.ejb.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.model.Employee;

@Stateful
public class EmployeeEndpoint extends AbstractEndpoint{

    @EJB
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

    public String getCurrentLogin(){
        return sessionContext.getCallerPrincipal().getName();
    }
    
    public Employee findLogin(String login){
        return employeefacade.findLogin(login);
    }
    
//    public Konto znajdzLogin(String login) {
//        return kontoFacade.znajdzLogin(login);
//    }
    
    public Employee getCurrentAccount() {
        return findLogin(getCurrentLogin());
    }
            
    
}
