/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.web.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.ejb.endpoint.NotificationEndpoint;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.web.controller.NotificationController;

/**
 *
 * @author Warsztat
 */
@Named
@RequestScoped
public class EditNotificationBean {

    public EditNotificationBean() {
    }

    @Inject
    private NotificationEndpoint notificationEndpoint;

    @Inject
    private NotificationController notCtrl;
    
    @PostConstruct
    private void init() {
//        konto = kontoSession.getKontoEdytuj();
        notificationEnd = notCtrl.getNotificationEdit();
    }

    private Notification notificationEnd = new Notification();

    public Notification getNotification() {
        return notificationEnd;
    }

    public String getNotificationToEdit(Notification notification) {
        notificationEnd = notCtrl.getNotificationToEdit(notification);
        return "userNotificationChange";
    }

    public String saveNotificationAfterEdit() {
        return notCtrl.saveNotificationAfterEdit(notificationEnd);
    }

}
