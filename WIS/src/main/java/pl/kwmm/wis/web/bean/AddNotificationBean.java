package pl.kwmm.wis.web.bean;

import pl.kwmm.wis.web.controller.NotificationController;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Notification;


@Named
@RequestScoped
public class AddNotificationBean {

    @Inject
    private NotificationController notCtrl;

    private final Notification notification = new Notification();

    public Notification getNotification() {
        return notification;
    }

    public String register() {
            notCtrl.setRegisteredNotification(notification);
            return "userAddConfirm";       
    }
}
