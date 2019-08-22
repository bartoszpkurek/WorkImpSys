package pl.kwmm.wis.ejb.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.model.Employee_;

/**
 * Class Facade created from Session Beans For Entity Classes.
 * Transaction - Mandatory.
 * 
 * @see pl.kwmm.wis.model.Employee
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-06
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class EmployeeFacade extends AbstractFacade<Employee> {

    /**
     * Created from Session Beans For Entity Classes for PersistenceContext Instance.
     */
    @PersistenceContext(unitName = "wisPU")
    private EntityManager em;

    /**
     * Created from Session Beans For Entity Classes for EntityManager.
     * 
     * @return EntityManager 
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Constructor created from Session Beans For Entity Classes
     */
    public EmployeeFacade() {
        super(Employee.class);
    }
    
    
    /**
     * Method with Criteria Builder for finding proper single login from database.
     * 
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#findLogin(java.lang.String) 
     * 
     * @param login
     * @return Single Result of login from Database
     */
    public Employee findLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> from = query.from(Employee.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get(Employee_.login), login));
        TypedQuery<Employee> tq = em.createQuery(query);

        return tq.getSingleResult();
    }
    
}
