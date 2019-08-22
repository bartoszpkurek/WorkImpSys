package pl.kwmm.wis.ejb.endpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.ejb.facade.NotificationFacade;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.web.utils.ApplicationUtils;
import pl.kwmm.wis.web.utils.NotificationUtils;
import pl.kwmm.wis.web.utils.PointsUtils;

/**
 * Stateful Endpoint for business logic and performing Entity JPA methods.
 * Important! Avoid multi-threading after Injection as it's state could be
 * shared. Transaction - Requires New.
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
public class NotificationEndpoint extends AbstractEndpoint {

    /**
     * Initialization of the logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Facades Injection for Notification JPA data
     */
    @Inject
    private NotificationFacade notificationFacade;

    /**
     * Facades Injection for Employee JPA data
     */
    @Inject
    private EmployeeFacade employeeFacade;

    /**
     * Endpoint Injection for Employee data
     */
    @Inject
    private EmployeeEndpoint employeeEndpoint;

    //*****************Specific Methods
    /**
     * Method for registering Notification. Sets Rankingpoints to starting point
     * of zero and assign current logged user to the Notificiation. Logs info
     * message.
     *
     * @param n
     */
    @RolesAllowed("EMPLOYEE")
    public void registerNotification(Notification n) {
        n.setRankingpoints(0);
        n.setEmployee(employeeEndpoint.getCurrentAccount());
        n.addVoter(employeeEndpoint.getCurrentAccount());
        notificationFacade.edit(n);
        logger.info("User " + ApplicationUtils.getUserName() + " properly registered notification " + n.getCategory() + " with priority " + n.getPriority());
    }

    //*****************Grouped Methods for Loading Notifcations for different Views
    /**
     * Invoking Facade method for getting AllNotEndedNotification.
     *
     * @return AllNotifications
     */
    @PermitAll
    public List<Notification> getAllNotifications() {
        return notificationFacade.findAllNotEndedNotification();
    }

    /**
     * Invoking Facade method for getting MyNotification.
     *
     * @param employee
     * @return MyNotification
     */
    @PermitAll
    public List<Notification> getMyNotification(Employee employee) {
        return notificationFacade.findMyNotification(employee);
    }

    /**
     * Invoking Facade method for getting CompletedNotification.
     *
     * @return CompletedNotification
     */
    @PermitAll
    public List<Notification> getCompletedNotification() {
        return notificationFacade.findCompletedNotification();
    }

    /**
     * Invoking Facade method for getting TopThreeNotification.
     *
     * @return TopThreeNotification
     */
    @PermitAll
    public List<Notification> getTopThreeNotification() {
        return notificationFacade.findTopThreeNotification();
    }

    /**
     * Method for voting up.
     *
     * --Checker part-- Gets the current employeeEndpoint id. Creates list of
     * all voters for n Notification. Then stream the list into collection to
     * list with mapping Employee Entity - Employee_id long type. Returns List
     * of Employee Id with Long type.
     *
     * --Voting part-- Sets two temporal variables. "tmp" for storing current
     * rankingpoints, "mathtmp" for incrementation. Sets ranking points for
     * Entity n and sets current logged account as voter to prevent double
     * voting. Invokes facade edit method. Sets null to temporal variables to
     * zero them.
     *
     * @param n Notification Entity
     */
    @RolesAllowed("EMPLOYEE")
    public void voteUp(Notification n) {
        //Checking
        long idCurrentEmployee = employeeEndpoint.getCurrentAccount().getEmployee_id();
        List<Employee> listVoters = new ArrayList(n.getVoters());
        List<Long> ids = listVoters.stream()
                .map(Employee::getEmployee_id).collect(Collectors.toList());

        boolean voterID = ids.contains(idCurrentEmployee);

        //Voting
        if (voterID || n.getStatus() == Notification.NotificationStatus.Rejected) {
            logger.info("Already voted or rejected");
        } else {
            Integer tmp = n.getRankingpoints();
            Integer mathtmp = tmp + 1;
            n.setRankingpoints(mathtmp);
            n.addVoter(employeeEndpoint.getCurrentAccount());
            notificationFacade.edit(n);
            tmp = null;
            mathtmp = null;
        }
    }

    /**
     * Method for voting down.
     *
     * --Checker part-- Gets the current employeeEndpoint id. Creates list of
     * all voters for n Notification. Then stream the list into collection to
     * list with mapping Employee Entity - Employee_id long type. Returns List
     * of Employee Id with Long type.
     *
     * --Voting part-- Sets two temporal variables. "tmp" for storing current
     * rankingpoints, "mathtmp" for decrementation. Sets ranking points for
     * Entity n and sets current logged account as voter to prevent double
     * voting. Invokes facade edit method. Sets null to temporal variables to
     * zero them.
     *
     * @param n Notification Entity
     */
    @RolesAllowed("EMPLOYEE")
    public void voteDown(Notification n) {
        //Checking
        long idCurrentEmployee = employeeEndpoint.getCurrentAccount().getEmployee_id();
        List<Employee> listVoters = new ArrayList(n.getVoters());
        List<Long> ids = listVoters.stream()
                .map(Employee::getEmployee_id).collect(Collectors.toList());

        boolean voterID = ids.contains(idCurrentEmployee);

        //Voting
        if (voterID || n.getStatus() == Notification.NotificationStatus.Rejected) {
            logger.info("Already voted or rejected");
        } else {
            Integer tmp = n.getRankingpoints();
            Integer mathtmp = tmp - 1;
            n.setRankingpoints(mathtmp);
            n.addVoter(employeeEndpoint.getCurrentAccount());
            notificationFacade.edit(n);
            tmp = null;
            mathtmp = null;
        }
    }

    /**
     * Method for setting AWAITING status for Notification. Logs info message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void setStatusAwaiting(Notification n) {
        n.setStatus(Notification.NotificationStatus.Pending);
        notificationFacade.edit(n);
        logger.info("Status of notification " + n.getNotification_id() + " has been changed to " + n.getStatus() + " by " + ApplicationUtils.getUserName());
    }

    /**
     * Method for setting INIMPLEMENTATION status for Notification. Logs info
     * message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void setStatusInImplementation(Notification n) {
        n.setStatus(Notification.NotificationStatus.WIP);
        notificationFacade.edit(n);
        logger.info("Status of notification " + n.getNotification_id() + " has been changed to " + n.getStatus() + " by " + ApplicationUtils.getUserName());
    }

    /**
     * Method for setting Rejected status for Notification. Logs info message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void setStatusRejected(Notification n) {
        n.setStatus(Notification.NotificationStatus.Rejected);
        notificationFacade.edit(n);
        logger.info("Status of notification " + n.getNotification_id() + " has been changed to " + n.getStatus() + " by " + ApplicationUtils.getUserName());
    }

    /**
     * Method for REMOVING Notification. Logs info message.
     *
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void removeNotification(Notification n){
        notificationFacade.remove(n);
        logger.info("Notification " + n.getNotification_id() + " has been removed by " + ApplicationUtils.getUserName());
    }

    /**
     * Method for complete Notification and add points. Sets Employee from
     * notification, points variable from current points from Employee e,
     * counted points for this notification from countPoints method from
     * PointsUtils class. Then sum points and countedPoints and persist those
     * changes through notification and employee facade. Also sets status
     * COMPLETED for Notification. Logs info message.
     *
     * @see
     * pl.kwmm.wis.web.utils.PointsUtils#countPoints(pl.kwmm.wis.model.Notification)
     * @param n
     */
    @RolesAllowed("IMPTEAM")
    public void completeNotification(Notification n) {
        Employee e = n.getEmployee();
        int points = e.getNotificationpoints();
        int countedPoints = PointsUtils.countPoints(n);
        points = points + countedPoints;
        e.setNotificationpoints(points);
        employeeFacade.edit(e);
        logger.info("User " + e.getLogin() + " received points for completed notification ");
        n.setStatus(Notification.NotificationStatus.Completed);
        notificationFacade.edit(n);
        logger.info("Notification " + n.getNotification_id() + " status has been changed to " + n.getStatus() + " by " + ApplicationUtils.getUserName());
    }

    @PermitAll
    public Notification findNotificationById(Notification notification) {
        return notificationFacade.findNotificationById(notification.getNotification_id());
    }

    /**
     * Method for complete Editing Notification. Logs info message.
     *
     * @param notification
     */
    @PermitAll
    public void saveNotificationAfterEdit(Notification notification) {
        NotificationUtils.notificationEdit(notification, notification);
        notificationFacade.edit(notification);
        logger.info("Notification " + notification.getNotification_id() + " status has been successfully edited by " + ApplicationUtils.getUserName());
    }
// To be deleted if not needed
//    public void saveNotification(Notification n) {
//        notificationFacade.edit(n);
//    }
}
