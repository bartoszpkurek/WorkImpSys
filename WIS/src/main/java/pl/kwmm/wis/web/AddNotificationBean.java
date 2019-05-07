package pl.kwmm.wis.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Notification;


@Named
@RequestScoped
public class AddNotificationBean {

    @Inject
    private NotificationController ctrl;

    private final Notification notification = new Notification();

    public Notification getNotification() {
        return notification;
    }

    public String register() {
            ctrl.setRegisteredNotification(notification);
            return "userAddConfirm";       
    }
}
