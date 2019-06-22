package pl.kwmm.wis.web.bean.employee;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

/**
 * Named Bean for confirming registering Employee
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-20
 */
@Named
@RequestScoped
public class EditEmployeeBean {

//    Wyrzucic kiedy niepotrzebne
//    public EditEmployeeBean() {
//    }

    /**
     * Initialization of the logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * Employee Field
     */
    private Employee employee = new Employee();

    /**
     * Controller Injection for Employee data
     */
    @Inject
    private EmployeeController empCtrl;

    //**************METHODS
    /**
     * Method after construction of the object to set Employee variable to currentAccount
     */
    @PostConstruct
    private void init() {
        employee = empCtrl.getCurrentAccount();
    }

    /**
     * Method to invoke saving method from Controller.
     * 
     * @return invoking method from Controller
     */
    public String changeMyData() {
        logger.info("Starting editing process of account: "
                + employee.getLogin()
                + " by "
                + empCtrl.getCurrentAccount());
        return empCtrl.changeMyData(employee);
    }
    
    //**************STANDARD GETTER
    public Employee getEmployee() {
        return employee;
    }
}
