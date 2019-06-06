package pl.kwmm.wis.web.utils;

import java.security.Principal;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Klasa narzedziowa
 * 
 */

public class ApplicationUtils {
    
    public static ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public static String getUserName() {
        Principal p = getContext().getUserPrincipal();
        return (null == p ? "Anonymous" : p.getName());
    }

    public static void invalidateSession() {
        ((HttpSession) getContext().getSession(true)).invalidate();
    }
}
