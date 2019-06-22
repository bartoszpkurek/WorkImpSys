package pl.kwmm.wis.web.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import pl.kwmm.wis.web.utils.ApplicationUtils;

@Named("accountSession")
@SessionScoped
public class UtilsAccountSessionBean implements Serializable {

    public String logout() throws IOException {
        ApplicationUtils.invalidateSession();
        reload();
        return "homePage";
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }



}
