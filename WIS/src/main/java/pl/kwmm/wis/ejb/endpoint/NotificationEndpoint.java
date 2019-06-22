package pl.kwmm.wis.ejb.endpoint;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import pl.kwmm.wis.ejb.facade.EmployeeFacade;
import pl.kwmm.wis.ejb.facade.NotificationFacade;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.web.bean.utils.AppUtilsBean;
import pl.kwmm.wis.web.utils.NotificationUtils;

@Stateful
public class NotificationEndpoint {

    @EJB
    private NotificationFacade notificationFacade;

    @EJB
    private EmployeeFacade employeeFacade;

    @EJB
    private EmployeeEndpoint employeeEndpoint;

    @Inject
    private AppUtilsBean appUtilsBean;

//    Dodatkowe metody dodajace date i punkty przy rejestracji Sugestii
    public void registerNotification(Notification n) {
        notificationFacade.create(n);
        n.setRankingpoints(0);
      
        n.setEmployee(employeeEndpoint.getCurrentAccount());
        n.addVoter(employeeEndpoint.getCurrentAccount());
        notificationFacade.edit(n);
    }

//    Metody rankingowe
    public void voteUp(Notification n) {

        Integer tmp = n.getRankingpoints();
        Integer mathtmp = tmp + 1;
        n.setRankingpoints(mathtmp);
        n.addVoter(employeeEndpoint.getCurrentAccount());
        notificationFacade.edit(n);

    }

    public void voteDown(Notification n) {

        Integer tmp = n.getRankingpoints();
        Integer mathtmp = tmp - 1;
        n.setRankingpoints(mathtmp);
        n.addVoter(employeeEndpoint.getCurrentAccount());
        notificationFacade.edit(n);

    }

    public void saveNotification(Notification n) {
        notificationFacade.edit(n);
    }

    public List<Notification> getAllNotifications() {
        return notificationFacade.findAllNotEndedNotification();
    }

    public void setStatusAwaiting(Notification n) {
        n.setStatus(Notification.NotificationStatus.Pending);
        notificationFacade.edit(n);
    }

    public void setStatusInImplementation(Notification n) {
        n.setStatus(Notification.NotificationStatus.WIP);
        notificationFacade.edit(n);
    }

    public void setStatusRejected(Notification n) {
        n.setStatus(Notification.NotificationStatus.Rejected);
        notificationFacade.edit(n);
    }

    public void removeNotification(Notification n) throws IOException {
        notificationFacade.remove(n);
        appUtilsBean.reload();
    }

    public List<Notification> getMyNotification(Employee employee) {
        return notificationFacade.findMyNotification(employee);
    }

    public List<Notification> getCompletedNotification() {
        return notificationFacade.findCompletedNotification();
    }

    public List<Notification> getTopThreeNotification() {
        return notificationFacade.findTopThreeNotification();
    }

    public void completeNotification(Notification n) {
        n.setStatus(Notification.NotificationStatus.Completed);
        Employee e = n.getEmployee();
        int points = e.getNotificationpoints();
        points = points + 10;
        e.setNotificationpoints(points);
        employeeFacade.edit(e);
        notificationFacade.edit(n);
    }

    public Notification findNotificationById(Notification notification) {
        return notificationFacade.findNotificationById(notification.getNotification_id());
    }

    
    
    
    public void saveNotificationAfterEdit(Notification notification) {

        NotificationUtils.notificationEdit(notification, notification);
        notificationFacade.edit(notification);


    }

}
