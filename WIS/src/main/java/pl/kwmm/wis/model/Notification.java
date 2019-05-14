package pl.kwmm.wis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id"),
    @NamedQuery(name = "Notification.findByShortdescription", query = "SELECT n FROM Notification n WHERE n.shortdescription = :shortdescription"),
    @NamedQuery(name = "Notification.findByFulldescription", query = "SELECT n FROM Notification n WHERE n.fulldescription = :fulldescription"),
    @NamedQuery(name = "Notification.findByDateadded", query = "SELECT n FROM Notification n WHERE n.dateadded = :dateadded"),
    @NamedQuery(name = "Notification.findByMoneyrequired", query = "SELECT n FROM Notification n WHERE n.moneyrequired = :moneyrequired"),
    @NamedQuery(name = "Notification.findByPriority", query = "SELECT n FROM Notification n WHERE n.priority = :priority"),
    @NamedQuery(name = "Notification.findByCategory", query = "SELECT n FROM Notification n WHERE n.category = :category"),
    @NamedQuery(name = "Notification.findByStatus", query = "SELECT n FROM Notification n WHERE n.status = :status"),
    @NamedQuery(name = "Notification.findByRankingpoints", query = "SELECT n FROM Notification n WHERE n.rankingpoints = :rankingpoints"),
    @NamedQuery(name = "Notification.findByVoted", query = "SELECT n FROM Notification n WHERE n.voted = :voted")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "shortdescription")
    private String shortdescription;

    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "fulldescription", nullable = false)
    private String fulldescription;

    @Basic(optional = false)
    @Column(name = "dateadded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateadded;

    @Basic(optional = false)
    @Column(name = "moneyrequired", nullable = false)
    private boolean moneyrequired;

    @Basic(optional = false)
    @Column(name = "priority", nullable = false)
    private int priority;

    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "category", nullable = false)
    private String category;

    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "status", nullable = false)
    private String status;
   
    @Column(name = "rankingpoints")
    private Integer rankingpoints;
   
    @Column(name = "voted")
    private Integer voted;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(Integer id, String shortdescription, String fulldescription, Date dateadded, boolean moneyrequired, int priority, String category, String status) {
        this.id = id;
        this.shortdescription = shortdescription;
        this.fulldescription = fulldescription;
        this.dateadded = dateadded;
        this.moneyrequired = moneyrequired;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getFulldescription() {
        return fulldescription;
    }

    public void setFulldescription(String fulldescription) {
        this.fulldescription = fulldescription;
    }

    public Date getDateadded() {
        return dateadded;
    }

    public void setDateadded(Date dateadded) {
        this.dateadded = dateadded;
    }

    public boolean getMoneyrequired() {
        return moneyrequired;
    }

    public void setMoneyrequired(boolean moneyrequired) {
        this.moneyrequired = moneyrequired;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRankingpoints() {
        return rankingpoints;
    }

    public void setRankingpoints(Integer rankingpoints) {
        this.rankingpoints = rankingpoints;
    }

    public Integer getVoted() {
        return voted;
    }

    public void setVoted(Integer voted) {
        this.voted = voted;
    }
}
