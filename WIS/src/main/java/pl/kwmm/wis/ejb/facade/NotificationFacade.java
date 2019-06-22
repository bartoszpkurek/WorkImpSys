package pl.kwmm.wis.ejb.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;

/**
 *
 * @author Warsztat
 */
@Stateless
public class NotificationFacade extends AbstractFacade<Notification> {

    @PersistenceContext(unitName = "wisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificationFacade() {
        super(Notification.class);
    }

    public List<Notification> findMyNotification(Employee employee) {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByEmployeeId", Notification.class);
        tq.setParameter("employee", employee);
        return tq.getResultList();
    }
    
    public List<Notification> findCompletedNotification() {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByStatusEnded", Notification.class);
        return tq.getResultList();
    }
    
    public List<Notification> findAllNotEndedNotification() {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByStatusNotEnded", Notification.class);
        return tq.getResultList();
    }
    
    public List<Notification> findTopThreeNotification() {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByTopThree", Notification.class).setMaxResults(3);
        return tq.getResultList();
    }

    public Notification findNotificationById(long notification_id) {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByNotificationId", Notification.class);
        tq.setParameter("notification", notification_id);
        return tq.getSingleResult();
        

    }
    
}
