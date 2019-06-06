package pl.kwmm.wis.web.bean.employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

/** Named Bean for registering Employee.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Named
@RequestScoped
public class AddEmployeeBean {

    /** Controller Injection for Employee data.
     *
     */
    @Inject
    private EmployeeController empCtrl;

    //*****************Fields
    private final Employee employee = new Employee();
    private String passwordRepeat = "";

    //*****************Methods
    /**
     * Register Method
     * Used fields:
     * "password" from AddEmployeeBean,
     * "passwordRepeat" from AddEmployeBean,
     * "RegisteredEmployee" from EmployeeController.
     *
     * Method for setting the RegisteredEmployee field,
     * if "passwordRepeat" field equals "password" field from Employee Entity.
     * Sets employee field as RegisteredEmployee field
     * in Employee controler by a setter.
     * If false returns null.
     *
     * @see pl.kwmm.wis.model.Employee#getPassword()
     * @see pl.kwmm.wis.web.controller.EmployeeController
     * @see pl.kwmm.wis.web.controller.EmployeeController#setRegisteredEmployee(pl.kwmm.wis.model.Employee)
     *
     * @return employeeAddConfirm String for faces-config.xml navigation rules
     */
    public String register() {
        if (passwordRepeat.equals(employee.getPassword())) {
            empCtrl.setRegisteredEmployee(employee);
            return "employeeAddConfirm";
        } else {
            return null;
        }
    }

    //*****************Only Getters
    public Employee getEmployee() {
        return employee;
    }

    //*****************Standard Getters/Setters
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
