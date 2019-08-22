package pl.kwmm.wis.web.bean.employee;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.exception.BaseException;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;
import pl.kwmm.wis.web.utils.EmployeeUtils;

/**
 * Named Bean for admin options for Employee accounts. Provides options for
 * deleting, disabling, enabling, change roles accounts.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Named("empView")
@RequestScoped
@RolesAllowed("ADMIN")
public class ViewEmployeeBean {

    /**
     * Initialization of the logger.
     *
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller Injection for Employee data.
     *
     */
    @Inject
    private EmployeeController empCtrl;

    //*****************Fields
    private List tableDataList;

    //*****************Methods
    /**
     * Method for returning all Employee from Facade as list to enable sorting
     * view.
     *
     * @return List of Employee
     */
    public List getTableDataList() {
        if (tableDataList == null) {
            tableDataList = empCtrl.getAllEmployee();
        }
        return tableDataList;
    }

    /**
     * Method for invoking enabling Employee through methods of
     * EmployeeController and EmployeeEndpoint. Logs debug message.
     *
     * @param e - Employee Object
     */
    public void enableEmployee(Employee e) {
        logger.debug("Starting process of ENABLING account....");
        empCtrl.enableEmployee(e);
    }

    /**
     * Method for invoking disabling Employee through methods of
     * EmployeeController and EmployeeEndpoint. Logs debug message.
     *
     * @param e - Employee Object
     */
    public void disableEmployee(Employee e) {
        logger.debug("Starting process of DISABLING account....");
        empCtrl.disableEmployee(e);
    }

    /**
     * Method for invoking deleting Employee through methods of
     * EmployeeController and EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void deleteEmployee(Employee e) {
        logger.debug("Starting process of DELETING account...");
        empCtrl.deleteEmployee(e);
    }

    /**
     * Method for invoking changing role to Employee through methods of
     * EmployeeController and EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void setEmployeeRole(Employee e) {
        logger.debug("Starting process of CHANGING ROLE to EMPLOYEE...");
        empCtrl.setEmployeeRole(e);
    }

    /**
     * Method for invoking changing role to ImpTeam through methods of
     * EmployeeController and EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void setImpTeamRole(Employee e) {
        logger.debug("Starting process of CHANGING ROLE to IMPTEAM...");
        empCtrl.setImpTeamRole(e);
    }

    /**
     * Method for invoking changing role to Admin through methods of
     * EmployeeController and EmployeeEndpoint.
     *
     * @param e - Employee Object
     */
    public void setAdminRole(Employee e) {
        logger.debug("Starting process of CHANGING ROLE to ADMIN...");
        empCtrl.setAdminRole(e);
    }

    /**
     * Method for resetting password. Initialize String for tempPassword with
     * randomized method from Employee Utils class. Invokes reset password from
     * Employee with Employee object and tempPassword. Uses Faces message for
     * showing the password in a dialog box.
     *
     * @param e Employee Object
     * @throws BaseException
     */
    public void resetPassword(Employee e) throws BaseException {
        String tempPassword = EmployeeUtils.passwordChange();
        empCtrl.resetPassword(e, tempPassword);
        FacesMessage message = new FacesMessage("New Password", tempPassword);
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
    }
}
