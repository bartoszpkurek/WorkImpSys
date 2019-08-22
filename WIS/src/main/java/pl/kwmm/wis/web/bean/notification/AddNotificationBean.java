package pl.kwmm.wis.web.bean.notification;

import pl.kwmm.wis.web.controller.NotificationController;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.model.Notification;

/**
 * Named Bean for registering new notifications. Creates new Notification
 * instance and set it as registered one in Notification Controller.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-07-13
 */
@Named
@RequestScoped
public class AddNotificationBean {

    /**
     * Initialization of the logger.
     *
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Controller Injection for Notification data.
     */
    @Inject
    private NotificationController notCtrl;

    //*****************Fields
    private final Notification notification = new Notification();

    //*****************Method
    /**
     * Invoke method for setting registered notification field from Notification
     * Controller.
     *
     * @return navigation rule
     */
    public String register() {
        notCtrl.setRegisteredNotification(notification);
        return "userAddConfirm";
    }

    //*****************Field Getter
    public Notification getNotification() {
        return notification;
    }

}
