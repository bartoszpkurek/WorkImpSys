package pl.kwmm.wis.web.bean;

import ch.qos.logback.classic.pattern.Util;
import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class UtilsLanguageBean implements Serializable {

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private String language = locale.toString();

//    private boolean checkLanguagePl = checkLanguagePl();

//    public boolean checkLanguagePl() {
//        if (language.equals("pl")) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}
