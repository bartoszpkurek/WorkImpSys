package pl.kwmm.wis.web.bean;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

@ManagedBean(name = "empView")
@RequestScoped
public class ViewEmployeeBean {
    
    @Inject
    private EmployeeController empCtrl;
    
    private List tableDataList;

    public List getTableDataList() {
        if (tableDataList == null) {
            tableDataList = empCtrl.getAllEmployee();
        }
        return tableDataList;
    }
    
    public void deleteEmployee(Employee e){
        empCtrl.deleteEmployee(e);
    }
    
    public void enableEmployee(Employee e){
        empCtrl.enableEmployee(e);
    }
    
    public void disableEmployee(Employee e){
        empCtrl.disableEmployee(e);
    }
    
}
