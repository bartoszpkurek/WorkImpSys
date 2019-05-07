package pl.kwmm.wis.web;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import pl.kwmm.wis.ejb.endpoint.NotificationEndpoint;
import pl.kwmm.wis.model.Notification;

@SessionScoped
public class NotificationController implements Serializable {

    @EJB
    NotificationEndpoint notificationEndpoint;

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

}
