/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.web.bean;

import pl.kwmm.wis.web.controller.NotificationController;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Notification;

/**
 *
 * @author Warsztat
 */
@Named
@RequestScoped
public class AddNotificationConfimBean {

    @Inject
    private NotificationController notCtrl;

    public Notification getNotification() {
        return notCtrl.getRegisteredNotification();
    }
    
    public String register() {
        notCtrl.registerNotification();
        return "success";
    }

}
