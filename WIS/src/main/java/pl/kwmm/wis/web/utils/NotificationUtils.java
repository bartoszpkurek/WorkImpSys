/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.web.utils;

import pl.kwmm.wis.model.Notification;

/**
 *
 * @author Warsztat
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
