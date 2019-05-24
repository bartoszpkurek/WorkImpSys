package pl.kwmm.wis.web.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Employee;

@Named("employeeDetailsBean")
@RequestScoped
public class EmployeeDetailsBean {

    public EmployeeDetailsBean() {
    }

    @Inject
    private UtilsAccountSessionBean accountSessionBean;

    private Employee employee = new Employee();

    @PostConstruct
    private void init() {
        employee = accountSessionBean.getCurrentAccount();
    }

    public Employee getEmployee() {
        return employee;
    }

}
