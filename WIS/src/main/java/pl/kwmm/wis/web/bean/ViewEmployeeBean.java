package pl.kwmm.wis.web.bean;

import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import org.primefaces.PrimeFaces;
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

    public void deleteEmployee(Employee e) {
        empCtrl.deleteEmployee(e);
    }

    public void enableEmployee(Employee e) {
        empCtrl.enableEmployee(e);
    }

    public void disableEmployee(Employee e) {
        empCtrl.disableEmployee(e);
    }

    public void setImpTeamRole(Employee e) {
        empCtrl.setImpTeamRole(e);
    }

    public void setAdminRole(Employee e) {
        empCtrl.setAdminRole(e);
    }

    public void setEmployeeRole(Employee e) {
        empCtrl.setEmployeeRole(e);
    }

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
     
    
    
    public void resetPassword(Employee e) throws IOException {
        empCtrl.resetPassword(e);
        
        FacesMessage message = new FacesMessage("New Password", e.getPassword());
        PrimeFaces.current().dialog().showMessageDynamic(message, true);
        
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

}
