package pl.kwmm.wis.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

@Named
@RequestScoped
public class AddEmployeeBean {

    @Inject
    private EmployeeController empCtrl;
    
    private final Employee employee = new Employee();
    private String passwordRepeat = "";

    public Employee getEmployee() {
        return employee;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String register() {
        if (passwordRepeat.equals(employee.getPassword())) {
            empCtrl.setRegisteredEmployee(employee);
            return "employeeAddConfirm";
        } else {
            return null;
        }
    }
}
