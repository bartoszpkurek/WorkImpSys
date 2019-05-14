package pl.kwmm.wis.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;


@Named
@RequestScoped
public class AddEmployeeConfirmBean {
    
    @Inject
    private EmployeeController empCtrl;
    
    public Employee getEmployee(){
        return empCtrl.getRegisteredEmployee();
    }
    
    public String register(){
        if(getEmployee().getType().equals("Employee")){
            empCtrl.registerEmployee();
            return "employeeUserReg";
        } else {
            empCtrl.registerEscalatedEmployee();
            return "employeeEscalatedReg";
        }
    }
    
    
}
