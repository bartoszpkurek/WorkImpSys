package pl.kwmm.wis.web.bean.notification;

import pl.kwmm.wis.web.bean.utils.AppUtilsBean;
import pl.kwmm.wis.web.controller.NotificationController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.exception.BaseException;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.web.controller.EmployeeController;

/**
 * Named Bean for Employee/ImpTeam options for Notifications. Provides options
 * for voting, removing, seting status, completing notifications.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-07-13
 */
@Named("notView")
@RequestScoped
public class ViewNotificationBean {

    /**
     * Initialization of the logger.
     *
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller Injection for Notification data.
     */
    @Inject
    private NotificationController ctrl;

    /**
     * Controller Injection for Employee data.
     */
    @Inject
    private EmployeeController empCtrl;

    /**
     * Bean Injection for getting notification to edit data.
     *
     * @see
     * pl.kwmm.wis.web.bean.EditNotificationBean#getNotificationToEdit(pl.kwmm.wis.model.Notification)
     */
    @Inject
    private EditNotificationBean editBean;

    /**
     * Bean Injection for reloading page utility.
     */
    @Inject
    private AppUtilsBean appUtilsBean;

    /**
     * Default constructor without parameters
     */
    public ViewNotificationBean() {
    }

    //*****************Fields
    private List tableDataList;
    private List myDataList;
    private List topThreeDataList;
    private List completedDataList;

    //*****************POST Construct
    @PostConstruct
    private void init() {
        employee = empCtrl.getCurrentAccount();
    }

    private Employee employee = new Employee();

    //*****************Methods
    /**
     * Method for checking if rejected for notificationView.xhtml
     * @param n
     * @return boolean true/false
     */
    public boolean isRejected(Notification n) {
        if (n.getStatus() == Notification.NotificationStatus.Rejected) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Method for invoking method for voting from Notification Controller
     *
     * @param n
     */
    @RolesAllowed("EMPLOYEE")
    public void voteUp(Notification n) {
        ctrl.voteUp(n);
    }

    /**
     * Method for invoking method for voting from Notification Controller
     *
     * @param n
     */
    @RolesAllowed("EMPLOYEE")
    public void voteDown(Notification n) {
        ctrl.voteDown(n);
    }

    /**
     * Method for invoking method for setting AWAITING status from Notification
     * Controller. Logs debug message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void setStatusAwaiting(Notification n) {
        logger.debug("Starting process of setting AWAITING status....");
        ctrl.setStatusAwaiting(n);
    }

    /**
     * Method for invoking method for setting INIMPLEMENTATION status from
     * Notification Controller. Logs debug message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void setStatusInImplementation(Notification n) {
        logger.debug("Starting process of setting INIMPLEMENTATION status....");
        ctrl.setStatusInImplementation(n);
    }

    /**
     * Method for invoking method for setting REJECTED status from Notification
     * Controller. Logs debug message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void setStatusRejected(Notification n) {
        logger.debug("Starting process of setting REJECTED status....");
        ctrl.setStatusRejected(n);
    }

    /**
     * Method for invoking method for REMOVING notifications from Notification
     * Controller. Logs debug message.
     *
     * @param n
     * @throws BaseException
     */
    @RolesAllowed("IMPTEAM")
    public void removeNotification(Notification n) throws BaseException {
        logger.debug("Starting process of REMOVING Notification....");
        ctrl.removeNotification(n);
        appUtilsBean.reload();
    }

    /**
     * Method for invoking method for COMPLETING notifications from Notification
     * Controller. Reloads page. Logs debug message.
     *
     * @param n
     * @throws BaseException
     */
    @RolesAllowed("IMPTEAM")
    public void completeNotification(Notification n) throws BaseException {
        logger.debug("Starting process of COMPLETINGs Notification....");
        ctrl.completeNotification(n);
        appUtilsBean.reload();
    }

    /**
     * Method for returning Notification for edition.
     *
     * @param n
     * @return Notification
     */
    @PermitAll
    public String getNotificationToEdit(Notification n) {
        return editBean.getNotificationToEdit(n);
    }

    //*****************Fields Getters
    /**
     * Setting tableDataList field with AllNotEndedNotification data from
     * Controller.
     *
     * @return tableDataList
     */
    @PermitAll
    public List getTableDataList() {
        if (tableDataList == null) {
            tableDataList = ctrl.getAllNotifications();
        }
        return tableDataList;
    }

    /**
     * Setting tableDataList field with MyNotification data from Controller for
     * current logged in Employee.
     *
     * @param employee
     * @return myDataList
     */
    @PermitAll
    public List getMyDataList(Employee employee) {
        if (myDataList == null) {
            myDataList = ctrl.getMyNotification(empCtrl.getCurrentAccount());
        }
        return myDataList;
    }

    /**
     * Setting topThreeDataList field with TopThreeNotification data from
     * Controller.
     *
     * @return topThreeDataList
     */
    @PermitAll
    public List getTopThreeDataList() {
        if (topThreeDataList == null) {
            topThreeDataList = ctrl.getTopThreeNotification();
        }
        return topThreeDataList;
    }

    /**
     * Setting topThreeDataList field with CompletedNotification data from
     * Controller.
     *
     * @return completedDataList
     */
    @PermitAll
    public List getCompletedDataList() {
        if (completedDataList == null) {
            completedDataList = ctrl.getCompletedNotification();
        }
        return completedDataList;
    }

    //*****************Field Getter
    public Employee getEmployee() {
        return employee;
    }
}
