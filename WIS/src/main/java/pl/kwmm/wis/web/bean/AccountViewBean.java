package pl.kwmm.wis.web.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import pl.kwmm.wis.web.controller.EmployeeController;

@ManagedBean(name = "accView")
@ViewScoped
public class AccountViewBean {
    
    @Inject
    private EmployeeController empCtrl;
    
    private List tableDataList;

    public List getTableDataList() {
        if (tableDataList == null) {
            tableDataList = empCtrl.getAllEmployee();
        }
        return tableDataList;
    }
    
}
