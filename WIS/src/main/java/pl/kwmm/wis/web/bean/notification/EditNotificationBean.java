package pl.kwmm.wis.web.bean.notification;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.ejb.endpoint.NotificationEndpoint;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.web.controller.NotificationController;

/**
 * Named Bean for Editing Notifications. Provides options for loading/saving
 * notification for Editing.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-07-13
 */
@Named
@RequestScoped
public class EditNotificationBean {

     /**
     * Initialization of the logger.
     *
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * Default constructor without parameters
     */
    public EditNotificationBean() {
    }

    /**
     * Endpoint Injection for Notification data.
     */
    @Inject
    private NotificationEndpoint notificationEndpoint;

    /**
     * Controller Injection for Notification data.
     */
    @Inject
    private NotificationController notCtrl;

    //*****************Fields
    private Notification notificationEnd = new Notification();

    //*****************POST Construct
    @PostConstruct
    private void init() {
        notificationEnd = notCtrl.getNotificationEdit();
    }

    //*****************Methods
    /**
     * Sets notificationEnd field with notification taken from Controller.
     * @param notification
     * @return Navigation rule
     */
    public String getNotificationToEdit(Notification notification) {
        logger.debug("Starting process of Editing Notification....");
        notificationEnd = notCtrl.getNotificationToEdit(notification);
        return "userNotificationChange";
    }

    /**
     * Invokes method for saving edited Notification from Controller
     * @return Navigation rule 
     */
    public String saveNotificationAfterEdit() {
        logger.debug("Starting process of Editing Notification....");
        notCtrl.saveNotificationAfterEdit(notificationEnd);
        return "homePage";
    }

    //*****************Fields Getters
    public Notification getNotification() {
        return notificationEnd;
    }
}
