package pl.kwmm.wis.ejb.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;

/**
 * @author Warsztat
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
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

    /**
     * Invoking findByEmployeeId NamedQuery with Notification object for current
     * logged in Employee
     *
     * @param employee
     * @return List of MyNotification
     */
    public List<Notification> findMyNotification(Employee employee) {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByEmployeeId", Notification.class);
        tq.setParameter("employee", employee);
        return tq.getResultList();
    }

    /**
     * Invoking findByStatusEnded NamedQuery with Notification object
     *
     * @return List of CompletedNotification
     */
    public List<Notification> findCompletedNotification() {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByStatusEnded", Notification.class);
        return tq.getResultList();
    }

    /**
     * Invoking findByStatusNotEnded NamedQuery with Notification object
     *
     * @return List of AllNotEndedNotification
     */
    public List<Notification> findAllNotEndedNotification() {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByStatusNotEnded", Notification.class);
        return tq.getResultList();
    }

    /**
     * Invoking findByTopThree NamedQuery with Notification object
     *
     * @return List of TopThreeNotification
     */
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
