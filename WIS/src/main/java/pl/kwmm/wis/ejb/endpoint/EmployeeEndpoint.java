package pl.kwmm.wis.ejb.endpoint;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.utils.ApplicationUtils;
import pl.kwmm.wis.web.utils.EmployeeUtils;

/**
 * Stateful Endpoint for business logic and performing Entity JPA methods.
 * Important! Avoid multi-threading after Injection as it's state could be
 * shared.
 *
 * LocalBean for no-interface view.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//@Interceptors(LoggingInterceptor.class)
public class EmployeeEndpoint extends AbstractEndpoint {

    /**
     * Initialization of the logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Facades Injection for Employee JPA data
     */
    @Inject
    private EmployeeFacade employeefacade;

    //*****************Methods
    /**
     * Method to pass register Employee into Database. Before creating sets
     * status to true and notification points to 0 for starter point. Lastly
     * calling method from EmployeeFacade for create JPA entry in database. Logs
     * info message.
     *
     * @see pl.kwmm.wis.web.controller.EmployeeController#registerEmployee()
     *
     * @param e is Employee instance
     */
    public void registerUserAccount(Employee e) throws NoSuchAlgorithmException {
        e.setPassword(hashPassword(e));
        e.setStatus(true);
        logger.info(e.getType() + " account " + e.getLogin() + " has been automatically activated.");
        e.setNotificationpoints(0);
        employeefacade.create(e);
        logger.info("User " + ApplicationUtils.getUserName() + " properly registered account " + e.getType() + " with login " + e.getLogin());
    }

    /**
     * Method to pass register Admin/ImpTeam Employee into Database. Before
     * creating: sets status to false to prevent login, sets lasttype to type
     * chosen on register form to automating admin enabling account, sets type
     * for type from enum - DISABLED (same as one role for jdbc), sets
     * notification points to 0 for starter point. Lastly calling method from
     * EmployeeFacade to create JPA entry in database. Logs warning message.
     *
     * @see pl.kwmm.wis.web.controller.EmployeeController#registerEmployee()
     *
     * @param e is Employee instance
     */
    public void registerEscalatedAccount(Employee e) throws NoSuchAlgorithmException{
        e.setPassword(hashPassword(e));
        e.setStatus(false);
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.Disabled);
        logger.warn(e.getLasttype() + " account " + e.getLogin() + " has been automatically deactivated as newly created.");
        e.setNotificationpoints(0);
        employeefacade.create(e);
        logger.warn("User " + ApplicationUtils.getUserName() + " properly registered escalated account " + e.getLasttype() + " with login " + e.getLogin() + ". Account needs verification and activation.");
    }

    /**
     * Method for enabling Employee account. sets status, sets type to lasttype
     * set before, clears lasttype. Lastly calling method from EmployeeFacade to
     * merge JPA entry in database. Logs warn message.
     *
     * @param e - Employee Object
     */
    public void enableEmployee(Employee e) {
        e.setStatus(true);
        e.setType(e.getLasttype());
        e.setLasttype(null);
        employeefacade.edit(e);
        logger.warn(e.getType() + " type account: " + e.getLogin() + " has been successfully ENABLED by: " + ApplicationUtils.getUserName());
    }

    /**
     * Method for disabling Employee account. sets status, sets lasttype to type
     * set before, sets type for type from enum - DISABLED (same as one role for
     * jdbc). Lastly calling method from EmployeeFacade to merge JPA entry in
     * database. Logs warn message.
     *
     * @param e - Employee Object
     */
    public void disableEmployee(Employee e) {
        e.setStatus(false);
        e.setLasttype(e.getType());
        e.setType(Employee.EmployeeType.Disabled);
        employeefacade.edit(e);
        logger.warn(e.getLasttype() + " type account: " + e.getLogin() + " has been successfully DISABLED by: " + ApplicationUtils.getUserName());
    }

    /**
     * Method for deleting Employee account. Calling method from EmployeeFacade
     * to remove JPA entry in database. Logs warn message.
     *
     * @param e - Employee Object
     */
    public void deleteEmployee(Employee e) {
        employeefacade.remove(e);
        logger.warn(e.getType() + " type account: " + e.getLogin() + " has been successfully DELETED by: " + ApplicationUtils.getUserName());
    }

    /**
     * Method for changing role to Employee. Condition - lastType != Employee.
     * Sets Lasttype to Type before change get from Entity. Sets Type to new
     * Type (Employee) Invokes EmployeeFacade method for merging. Logs change
     * into event log.
     *
     * @param e - Employee Object
     */
    public void setEmployeeRole(Employee e) {
        if (!e.getType().equals(Employee.EmployeeType.Employee)) {
            e.setLasttype(e.getType());
            e.setType(Employee.EmployeeType.Employee);
            employeefacade.edit(e);
            logger.info(e.getType() + " type account: "
                    + e.getLogin()
                    + " has been successfully MODIFIED by: "
                    + ApplicationUtils.getUserName()
                    + " to type "
                    + e.getType());
        } else {
            logger.warn(e.getType() + " type account: "
                    + e.getLogin()
                    + " has been unsuccessfully MODIFIED by: "
                    + ApplicationUtils.getUserName()
                    + " to type "
                    + e.getType()
                    + ". Account has already type: "
                    + e.getType());
        }
    }

    /**
     * Method for changing role to ImpTeam. Condition - lastType != Employee.
     * Sets Lasttype to Type before change get from Entity. Sets Type to new
     * Type (Employee) Invokes EmployeeFacade method for merging. Logs change
     * into event log.
     *
     * @param e - Employee Object
     */
    public void setImpTeamRole(Employee e) {
        if (!e.getType().equals(Employee.EmployeeType.ImpTeam)) {
            e.setLasttype(e.getType());
            e.setType(Employee.EmployeeType.ImpTeam);
            employeefacade.edit(e);
            logger.info(e.getType() + " type account: "
                    + e.getLogin()
                    + " has been successfully MODIFIED by: "
                    + ApplicationUtils.getUserName()
                    + " to type "
                    + e.getType());
        } else {
            logger.warn(e.getType() + " type account: "
                    + e.getLogin()
                    + " has been unsuccessfully MODIFIED by: "
                    + ApplicationUtils.getUserName()
                    + " to type "
                    + e.getType()
                    + ". Account has already type: "
                    + e.getType());
        }
    }

    /**
     * Method for changing role to Admin. Condition - lastType != Employee. Sets
     * Lasttype to Type before change get from Entity. Sets Type to new Type
     * (Employee) Invokes EmployeeFacade method for merging. Logs change into
     * event log.
     *
     * @param e - Employee Object
     */
    public void setAdminRole(Employee e) {
        if (!e.getType().equals(Employee.EmployeeType.Admin)) {
            e.setLasttype(e.getType());
            e.setType(Employee.EmployeeType.Admin);
            employeefacade.edit(e);
            logger.info(e.getType() + " type account: "
                    + e.getLogin()
                    + " has been successfully MODIFIED by: "
                    + ApplicationUtils.getUserName()
                    + " to type "
                    + e.getType());
        } else {
            logger.warn(e.getType() + " type account: "
                    + e.getLogin()
                    + " has been unsuccessfully MODIFIED by: "
                    + ApplicationUtils.getUserName()
                    + " to type "
                    + e.getType()
                    + ". Account has already type: "
                    + e.getType());
        }
    }

    /**
     * Method for editing account data. Checks if parameter isn't null. Creates
     * new Employee Instance and assign employee object. Invokes method from
     * EmployeeUtils class. Invokes EmployeeFacade method for merging. Logs
     * change into event log.
     *
     * @param employee
     */
    public void changeMyData(Employee employee) {
        if (null == employee) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        Employee employeeAcc = employee;
        EmployeeUtils.employeeEdit(employee, employeeAcc);
        employeefacade.edit(employeeAcc);
        logger.info("Fields of Account: "
                + employee.getLogin()
                + " have been successfully MODIFIED by: "
                + getCurrentLogin());
        employeeAcc = null;
    }

    /**
     * Method for changing Password. Create new Instance of current Account.
     * Uses hashPassword method for employee.
     * Sets Password and edits by facade Employee Object.
     * 
     * @param password
     */
    public void changeMyPassword(String password) throws NoSuchAlgorithmException {
        Employee employee = getCurrentAccount();
        employee.setPassword(hashString(password));
        employeefacade.edit(employee);
    }

    /**
     * Method for finding current logged account. Uses methods findLogin and
     * getCurrentLogin. Finds login based on current login from sessionContext
     *
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#getCurrentLogin()
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#findLogin()
     *
     * @return login of current account
     */
    public Employee getCurrentAccount() {
        return findLogin(getCurrentLogin());
    }

    /**
     * Method for getting current login from session Context from Abstract
     * Endpoint. Used for finding Employee Object based on login.
     *
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#findLogin()
     * @return Name of current login
     */
    public String getCurrentLogin() {
        return sessionContext.getCallerPrincipal().getName();
    }

    /**
     * Method for finding Employee Object based on login provided.
     *
     * @param login
     * @return Single Result from Query Builder from Employee Facade
     */
    public Employee findLogin(String login) {
        return employeefacade.findLogin(login);
    }

    /**
     * Method for resetting password by escalated account Admin. Invokes
     * Employee facade method for merging with hashing method.
     * Clears temporary variable.
     * 
     * @param e Employee Object
     * @param tempPassword 
     * @throws NoSuchAlgorithmException
     */
    public void resetPassword(Employee e, String tempPassword) throws NoSuchAlgorithmException{
        e.setPassword(hashString(tempPassword));
        employeefacade.edit(e);
        tempPassword = null;
    }

    /**
     * Method for listing all employees.
     *
     * @return List of Employees
     */
    public List<Employee> getAllEmployee() {
        return employeefacade.findAll();
    }

    /**
     * Hash method for Employee object param.
     * 
     * @param e
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public String hashPassword(Employee e) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(e.getPassword().getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash); // Java 8 feature

        return encoded;
    }
    
    /**
     * Hash method for String param.
     * @param password
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public String hashString(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash); // Java 8 feature

        return encoded;
    }

}
