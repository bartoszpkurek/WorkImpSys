package pl.kwmm.wis.ejb.endpoint;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.model.Employee;

@Stateful
public class EmployeeEndpoint {

    @EJB
    EmployeeFacade employeefacade;

    public void registerUserAccount(Employee e) {
        employeefacade.create(e);
        e.setStatus(true);
        e.setNotificationpoints(0);
    }

    public void registerEscalatedAccount(Employee e) {
        employeefacade.create(e);
        e.setStatus(false);
        e.setNotificationpoints(0);
    }

    public List<Employee> getAllEmployee(){
        return employeefacade.findAll();
    }
}
