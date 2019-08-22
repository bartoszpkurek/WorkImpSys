package pl.kwmm.wis.web.bean.utils;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import pl.kwmm.wis.exception.AppException;
import pl.kwmm.wis.exception.BaseException;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;
import pl.kwmm.wis.web.utils.ApplicationUtils;

/**
 * Named Bean for Application Utils. Contains Methods for login and reload.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-20
 */
@Named
@SessionScoped
public class AppUtilsBean implements Serializable {

    @Inject
    private EmployeeController empCtrl;

    //****************Field
    private Employee employee = new Employee();

    /**
     * Method after construction of the object to set Employee variable to
     * currentAccount
     */
    @PostConstruct
    private void init() {
        employee = empCtrl.getCurrentAccount();
    }

    /**
     * Method for logout. Invokes reload method for requestedURI.
     *
     * @return String for navigation rules.
     * @throws BaseException
     */
    public String logout() throws BaseException {
        ApplicationUtils.invalidateSession();
        reload();
        return "homePage";
    }

    /**
     * Method for reloading. Creates Instance of ExternalContext from
     * FacesContext. Redirects current context to new requested URI.
     *
     * @throws BaseException
     */
    public void reload() throws BaseException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ioe) {
            throw AppException.resourceLackException(ioe);
        }

    }

    //*****************Standard Getter
    public Employee getEmployee() {
        return employee;
    }
}
