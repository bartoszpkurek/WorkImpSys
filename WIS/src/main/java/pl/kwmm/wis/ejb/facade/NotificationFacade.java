/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.ejb.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Notification;
import pl.kwmm.wis.model.Notification_;

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
    
    public List<Notification> findTopThreeNotification() {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByTopThree", Notification.class).setMaxResults(3);
        return tq.getResultList();
    }

    public Notification findNotificationById(long notification_id) {
        TypedQuery<Notification> tq = em.createNamedQuery("Notification.findByNotificationId", Notification.class);
        tq.setParameter("notification", notification_id);
        return tq.getSingleResult();
        
        
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Notification> query = cb.createQuery(Notification.class);
//        Root<Notification> from = query.from(Notification.class);
//        query = query.select(from);
//        query = query.where(cb.equal(from.get(Notification_.notification_id), notification_id));
//        TypedQuery<Notification> tq = em.createQuery(query);
//
//        return tq.getSingleResult();
    }
    
}
