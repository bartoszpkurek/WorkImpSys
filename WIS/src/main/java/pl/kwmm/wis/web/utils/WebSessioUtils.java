/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.web.utils;

import java.security.Principal;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Warsztat
 */
public class WebSessioUtils {
    
    

    public WebSessioUtils() {
    }
    
    public static ExternalContext getContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public static String getUserName() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return (null == p ? "!BRAK UWIERZYTELNIENIA!" : p.getName());
    }

    public static void invalidateSession() {
        ((HttpSession) getContext().getSession(true)).invalidate();
    }
    
}
