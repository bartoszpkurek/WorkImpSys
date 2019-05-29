package pl.kwmm.wis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "Notification.findByEmployeeId", query = "SELECT n FROM Notification n WHERE n.employee = :employee"),
    @NamedQuery(name = "Notification.findByNotificationId", query = "SELECT n FROM Notification n WHERE n.notification_id = :notification"),
    @NamedQuery(name = "Notification.findByStatusEnded", query = "SELECT n FROM Notification n WHERE n.status = 'Zakonczone'"),
    @NamedQuery(name = "Notification.findByTopThree", query = "SELECT n FROM Notification n ORDER BY n.rankingpoints DESC")})

public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false)
    private long notification_id;


    @Size(min = 1, max = 255)
    @Column(name = "shortdescription")
    private String shortdescription;

    @Size(min = 1, max = 2147483647)
    @Column(name = "fulldescription", nullable = false)
    private String fulldescription;


    @Column(name = "dateadded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateadded;


    @Column(name = "moneyrequired", nullable = false)
    private boolean moneyrequired;


    @Column(name = "priority", nullable = false)
    private int priority;


    @Size(min = 1, max = 50)
    @Column(name = "category", nullable = false)
    private String category;


    @Size(min = 1, max = 50)
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "rankingpoints")
    private Integer rankingpoints;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id")
//    private Employee notificationOwner;
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Employee> tags = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public void addTag(Employee tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    
    public Notification() {
    }

    public Notification(long notification_id, String shortdescription, String fulldescription, Date dateadded, boolean moneyrequired, int priority, String category, String status, Integer rankingpoints, Employee employee) {
        this.notification_id = notification_id;
        this.shortdescription = shortdescription;
        this.fulldescription = fulldescription;
        this.dateadded = dateadded;
        this.moneyrequired = moneyrequired;
        this.priority = priority;
        this.category = category;
        this.status = status;
        this.rankingpoints = rankingpoints;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(long notification_id) {
        this.notification_id = notification_id;
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

    public boolean isMoneyrequired() {
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

    public List<Employee> getTags() {
        return tags;
    }

    public void setTags(List<Employee> tags) {
        this.tags = tags;
    }

}
