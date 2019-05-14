package pl.kwmm.wis.web.bean;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pl.kwmm.wis.web.utils.WebSessioUtils;

@Named("accountSession")
@SessionScoped
public class AccountSession implements Serializable {
    
    public String logout() {
        WebSessioUtils.invalidateSession();
        return "homePage";
    }
    
}
