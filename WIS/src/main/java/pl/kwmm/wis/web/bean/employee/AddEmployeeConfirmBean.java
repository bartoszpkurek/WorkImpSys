package pl.kwmm.wis.web.bean.employee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

/**
 * Named Bean for confirming registering Employee
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Named
@RequestScoped
public class AddEmployeeConfirmBean {
    
    /**
     * Controller Injection for Employee data
     */
    @Inject
    private EmployeeController empCtrl;
    
    /**
     * Register method for dividing registering into two methods from Employee Controller: "registerEmployee" and "registerEscalatedEmployee" 
     * depending if type is Employee or other (admin, impteam)
     * 
     * @see pl.kwmm.wis.web.controller.EmployeeController#registerEmployee() 
     * @see pl.kwmm.wis.web.controller.EmployeeController#registerEscalatedEmployee() () 
     * 
     * @return employeeUserReg or employeeEscalatedReg String for faces-config.xml navigation rules 
     */
    public String register(){
        if(getEmployee().getType().equals(Employee.EmployeeType.Employee)){
            empCtrl.registerEmployee();
            return "employeeUserReg";
        } else {
            empCtrl.registerEscalatedEmployee();
            return "employeeEscalatedReg";
        }
    }
    
    /**
     * Getter for receiving Employee Instance
     * 
     * @return Employee Instance from Employee Controller
     */
    public Employee getEmployee() {
        return empCtrl.getRegisteredEmployee();
    }
}
