package pl.kwmm.wis.web;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import pl.kwmm.wis.model.Notification;

@ManagedBean(name = "notView")
@ViewScoped
public class ViewBean implements Serializable {

    @Inject
    private NotificationController ctrl;
 
//    Dane wczytywane do listy, zeby PrimeFaces moglo sortowac
    private List tableDataList;

    public List getTableDataList() {
        if (tableDataList == null) {
            tableDataList = ctrl.getAllNotifications();
        }
        return tableDataList;
    }

    public void voteUp(Notification n) {
        ctrl.voteUp(n);
    }

    public void voteDown(Notification n) {
        ctrl.voteDown(n);
    }
}
