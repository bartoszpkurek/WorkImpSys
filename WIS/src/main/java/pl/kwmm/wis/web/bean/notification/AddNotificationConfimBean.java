package pl.kwmm.wis.web.bean.notification;

import pl.kwmm.wis.web.controller.NotificationController;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kwmm.wis.model.Notification;

/**
 * Named Bean for confirming adding new notifications. Provides method for
 * registering.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-07-13
 */
@Named
@RequestScoped
public class AddNotificationConfimBean {

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
    
    /**
     * Getter for Registered Notification from Controller
     * 
     * @return Registered Notification 
     */
    public Notification getNotification() {
        return notCtrl.getRegisteredNotification();
    }

    /**
     * Register Notification
     * 
     * @return Navigation rule
     */
    public String register() {
        logger.debug("Starting process of CONFIRMING REGISTERING Notification....");
        notCtrl.registerNotification();
        return "success";
    }

}
