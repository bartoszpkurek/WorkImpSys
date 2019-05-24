package pl.kwmm.wis.web.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;
import pl.kwmm.wis.web.utils.WebSessioUtils;

@Named("accountSession")
@SessionScoped
public class UtilsAccountSessionBean implements Serializable {

    @Inject
    private EmployeeController empCtrl;

    public String logout() throws IOException{
        WebSessioUtils.invalidateSession();
        reload();
        return "homePage";
    }

    public Employee getCurrentAccount() {
        return empCtrl.getCurrentAccount();
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

}
