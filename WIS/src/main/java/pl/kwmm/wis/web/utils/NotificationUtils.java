package pl.kwmm.wis.web.utils;

import pl.kwmm.wis.model.Notification;

/**
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-20
 */
public class NotificationUtils {
     public static void notificationEdit(Notification notificationFromForm, Notification notificationSaved) {

         if (null == notificationFromForm || null == notificationSaved) return;         
         
        notificationSaved.setShortdescription(notificationFromForm.getShortdescription());
        notificationSaved.setFulldescription(notificationFromForm.getFulldescription());
        notificationSaved.setCategory(notificationFromForm.getCategory());
        notificationSaved.setPriority(notificationFromForm.getPriority());

    }
    
}
