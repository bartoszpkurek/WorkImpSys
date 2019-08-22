package pl.kwmm.wis.web.bean.employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.exception.BaseException;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

/**
 * Named Bean for changing password.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Named
@RequestScoped
public class UserPasswordChangeBean {

    /**
     * Controller Injection for Employee data.
     */
    @Inject
    private EmployeeController empCtrl;

    //*****************Fields
    private Employee employee = new Employee();
    private String passwordRepeat = "";

    //*****************Methods
    /**
     * Method for changing Password.
     * Checks if"passwordRepeat" field equals "password" field from Employee Entity.
     * Invokes method from Controller to change 
     * If false returns null.
     *   
     * @return homePage String for faces-config.xml navigation rules
     * @throws BaseException
     */
    public String changeMyPassword() throws BaseException{
        if (passwordRepeat.equals(employee.getPassword())) {
            empCtrl.changeMyPassword(employee.getPassword());
            return "homePage";
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
