package pl.kwmm.wis.web.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.ejb.endpoint.NotificationEndpoint;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;

/**
 * Named Bean working as controller for Notificatio related data. Gathers all
 * methods in one place for Notification management and pass the methods/data to
 * Notification Endpoint for business logic. Does not have logger Instance.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-07-13
 */
@Named
@SessionScoped
public class NotificationController implements Serializable {

    /**
     * Endpoint Injection for Notification data
     */
    @Inject
    private NotificationEndpoint notificationEndpoint;

    //*****************Fields
    private Notification registeredNotification;
    private Notification notificationEdit = new Notification();

    //*****************Methods
    /**
     * Invoking Endpoint method for register Notification with
     * registeredNotification as argument of registeredNotification. Registered
     * notification is nulled.
     *
     */
    public void registerNotification() {
        notificationEndpoint.registerNotification(registeredNotification);
        registeredNotification = null;
    }

    //*****************Methods for Loading Notifcations for different Views
    /**
     * Invoking Endpoint method for getting AllNotEndedNotification.
     *
     * @return AllNotifications
     */
    public List<Notification> getAllNotifications() {
        return notificationEndpoint.getAllNotifications();
    }

    /**
     * Invoking Endpoint method for getting MyNotification.
     *
     * @param employee
     * @return MyNotification
     */
    public List<Notification> getMyNotification(Employee employee) {
        return notificationEndpoint.getMyNotification(employee);
    }

    /**
     * Invoking Endpoint method for getting CompletedNotification.
     *
     * @return CompletedNotification
     */
    public List<Notification> getCompletedNotification() {
        return notificationEndpoint.getCompletedNotification();
    }

    /**
     * Invoking Endpoint method for getting TopThreeNotification.
     *
     * @return TopThreeNotification
     */
    public List<Notification> getTopThreeNotification() {
        return notificationEndpoint.getTopThreeNotification();
    }

    //*****************Methods
    /**
     * Invoking method from Endpoint for voting up.
     *
     * @param n
     */
    public void voteUp(Notification n) {
        notificationEndpoint.voteUp(n);
    }

    /**
     * Invoking method from Endpoint for voting down.
     *
     * @param n
     */
    public void voteDown(Notification n) {
        notificationEndpoint.voteDown(n);
    }

    /**
     * Invoking method for setting AWAITING status from Notification Endpoint.
     *
     * @param n
     */
    public void setStatusAwaiting(Notification n) {
        notificationEndpoint.setStatusAwaiting(n);
    }
    /**
     * Invoking method for setting INIMPLEMENTATION status from Notification Endpoint.
     *
     * @param n
     */
    public void setStatusInImplementation(Notification n) {
        notificationEndpoint.setStatusInImplementation(n);
    }
    
    /**
     * Invoking method for setting REJECTED status from Notification Endpoint.
     *
     * @param n
     */
    public void setStatusRejected(Notification n) {
        notificationEndpoint.setStatusRejected(n);
    }

    //*****************TODO
    /**
     * Invoking method for REMOVING Notification from Endpoint.
     *
     * @param n
     */
    public void removeNotification(Notification n){
        notificationEndpoint.removeNotification(n);
    }


    /**
     * Invoking method for COMPLETING Notification from Endpoint.
     * 
     * @param n 
     */
    public void completeNotification(Notification n) {
        notificationEndpoint.completeNotification(n);
    }

    //*****************TODO     
    public void saveNotificationAfterEdit(Notification notification) {
        notificationEndpoint.saveNotificationAfterEdit(notification);

    }

    public Notification getNotificationToEdit(Notification notification) {
        notificationEdit = notificationEndpoint.findNotificationById(notification);
        return notificationEdit;
    }

    //*****************Getters/Setters
    public Notification getRegisteredNotification() {
        return registeredNotification;
    }

    public void setRegisteredNotification(Notification registeredNotification) {
        this.registeredNotification = registeredNotification;
    }

    public Notification getNotificationEdit() {
        return notificationEdit;
    }
}
