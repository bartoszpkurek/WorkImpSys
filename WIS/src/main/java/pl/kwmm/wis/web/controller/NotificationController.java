package pl.kwmm.wis.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint;
import pl.kwmm.wis.ejb.endpoint.NotificationEndpoint;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;

@SessionScoped
public class NotificationController implements Serializable {

    @EJB
    NotificationEndpoint notificationEndpoint;
    @EJB
    EmployeeEndpoint employeeEndpoint;
    
    private Notification registeredNotification;

    public Notification getRegisteredNotification() {
        return registeredNotification;
    }

    public void setRegisteredNotification(Notification registeredNotification) {
        this.registeredNotification = registeredNotification;
    }

    public void registerNotification() {
        notificationEndpoint.registerNotification(registeredNotification);
        registeredNotification = null;
    }

    public List<Notification> getAllNotifications() {
        return notificationEndpoint.getAllNotifications();
    }

    public void voteUp(Notification n) {
        notificationEndpoint.voteUp(n);
    }

    public void voteDown(Notification n) {
        notificationEndpoint.voteDown(n);
    }

    public void setStatusAwaiting(Notification n) {
        notificationEndpoint.setStatusAwaiting(n);
    }

    public void setStatusInImplementation(Notification n) {
        notificationEndpoint.setStatusInImplementation(n);
    }

    public void setStatusRejected(Notification n) {
        notificationEndpoint.setStatusRejected(n);
    }

    public void removeNotification(Notification n) throws IOException {
        notificationEndpoint.removeNotification(n);
    }

    public List<Notification> getMyNotification(Employee employee) {
        return notificationEndpoint.getMyNotification(employee);
    }

    public List<Notification> getCompletedNotification() {
        return notificationEndpoint.getCompletedNotification();
    }

    public List<Notification> getTopThreeNotification() {
        return notificationEndpoint.getTopThreeNotification();
    }

    public void completeNotification(Notification n) {
        notificationEndpoint.completeNotification(n);
    }
    
    public String saveNotificationAfterEdit(Notification notification) {
        notificationEndpoint.saveNotificationAfterEdit(notification);
        return "homePage";
    }
    
    private Notification notificationEdit = new Notification();

    public Notification getNotificationEdit() {
        return notificationEdit;
    }
    
    public Notification getNotificationToEdit(Notification notification) {
        notificationEdit = notificationEndpoint.findNotificationById(notification);
        return notificationEdit;
       
    }

}
