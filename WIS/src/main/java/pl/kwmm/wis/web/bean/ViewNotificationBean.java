package pl.kwmm.wis.web.bean;

import pl.kwmm.wis.web.bean.utils.AppUtilsBean;
import java.io.IOException;
import pl.kwmm.wis.web.controller.NotificationController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.web.controller.EmployeeController;

@ManagedBean(name = "notView")
@ViewScoped
public class ViewNotificationBean implements Serializable {

    @Inject
    private NotificationController ctrl;

    @Inject
    private EmployeeController empCtrl;

    @Inject
    private EditNotificationBean editBean;
    
    @Inject
    private AppUtilsBean appUtilsBean;

    public ViewNotificationBean() {
    }

//    Dane wczytywane do listy, zeby PrimeFaces moglo sortowac
    private List tableDataList;

    public List getTableDataList() {

        if (tableDataList == null) {
            tableDataList = ctrl.getAllNotifications();
        }
        return tableDataList;
    }

    @PostConstruct
    private void init() {
        employee = empCtrl.getCurrentAccount();
    }
    private Employee employee = new Employee();

    public Employee getEmployee() {
        return employee;
    }

    public List myDataList;

    public List getMyDataList(Employee employee) {
        if (myDataList == null) {
            return myDataList = ctrl.getMyNotification(empCtrl.getCurrentAccount());
        }
        return myDataList;
    }

    public List completedDataList;

    public List getCompletedDataList() {
        if (completedDataList == null) {
            return completedDataList = ctrl.getCompletedNotification();
        }
        return completedDataList;
    }

    public List topThreeDataList;

    public List getTopThreeDataList() {
        if (topThreeDataList == null) {
            return topThreeDataList = ctrl.getTopThreeNotification();
        }
        return topThreeDataList;
    }

    public void voteUp(Notification n) {
        ctrl.voteUp(n);
    }

    public void voteDown(Notification n) {
        ctrl.voteDown(n);
    }

    public void setStatusAwaiting(Notification n) {
        ctrl.setStatusAwaiting(n);
    }

    public void setStatusInImplementation(Notification n) {
        ctrl.setStatusInImplementation(n);
    }

    public void setStatusRejected(Notification n) {
        ctrl.setStatusRejected(n);
    }

    public void removeNotification(Notification n) throws IOException {
        ctrl.removeNotification(n);
    }

    public void completeNotification(Notification n) throws IOException {
        ctrl.completeNotification(n);
        appUtilsBean.reload();
    }

    public String getNotificationToEdit(Notification n) {
       return  editBean.getNotificationToEdit(n);
    }

    

}
