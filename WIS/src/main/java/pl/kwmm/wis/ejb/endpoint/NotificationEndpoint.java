package pl.kwmm.wis.ejb.endpoint;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import pl.kwmm.wis.ejb.facade.NotificationFacade;
import pl.kwmm.wis.model.Notification;

@Stateful
public class NotificationEndpoint {

    @EJB
    private NotificationFacade notificationFacade;

//    Dodatkowe metody dodajace date i punkty przy rejestracji Sugestii
    public void registerNotification(Notification n) {
        notificationFacade.create(n);
        n.setRankingpoints(0);
        Date cal = new Date();
        n.setDateadded(cal);
    }

//    Metody rankingowe
    public void voteUp(Notification n) {
        Integer tmp = n.getRankingpoints();
        Integer mathtmp = tmp + 1;
        n.setRankingpoints(mathtmp);
        notificationFacade.edit(n);
    }

    public void voteDown(Notification n) {
        Integer tmp = n.getRankingpoints();
        Integer mathtmp = tmp - 1;
        n.setRankingpoints(mathtmp);
        notificationFacade.edit(n);
    }

    public void saveNotification(Notification n) {
        notificationFacade.edit(n);
    }

    public List<Notification> getAllNotifications() {
        return notificationFacade.findAll();
    }
}
