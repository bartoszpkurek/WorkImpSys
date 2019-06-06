package pl.kwmm.wis.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import pl.kwmm.wis.web.utils.ApplicationUtils;

/**
 * Entity class for General Notification object. 
 * NOTIFICATION table contains data: Utils Fields/Identifiers/Relations
 * 
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-04
 */
@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "Notification.findByEmployeeId", query = "SELECT n FROM Notification n WHERE n.employee = :employee"),
    @NamedQuery(name = "Notification.findByStatusNotEnded", query = "SELECT n FROM Notification n WHERE n.status != pl.kwmm.wis.model.Notification.NotificationStatus.Completed"),
    @NamedQuery(name = "Notification.findByNotificationId", query = "SELECT n FROM Notification n WHERE n.notification_id = :notification"),
    @NamedQuery(name = "Notification.findByStatusEnded", query = "SELECT n FROM Notification n WHERE n.status = pl.kwmm.wis.model.Notification.NotificationStatus.Completed"),
    @NamedQuery(name = "Notification.findByTopThree", query = "SELECT n FROM Notification n ORDER BY n.rankingpoints DESC")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    //**************FIELDS
    /* 
        Utils fields:
        For checking unique id/versions/created date/created by/updated date/updated by user 
     */
    //Utils
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private long notification_id;

    //Utils
    @Column(name = "created_on")
    @NotNull
    private LocalDateTime createdOn;

    //Utils (should be always login of the user)
    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    //Utils (can be null on creation)
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    //Utils (can be null on creation)
    @Column(name = "updated_by")
    private String updatedBy;

    //Utils
    @Version
    private long version;

    /* 
        Specific account fields for NOTIFICATION table:
        Fields for account identifications: shortdescription, fulldescription, moneyrequired, ranking points
        Fields for enumerated values: priority, category, status,
        Fields for relationships: voters, employee
     */
    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Size(min = 25, max = 250)
    @Column(name = "shortdescription")
    private String shortdescription;

    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Lob
    @Size(min = 100, max = 1000)
    @Column(name = "fulldescription")
    private String fulldescription;

    //Identifier
    @Column(name = "moneyrequired")
    private boolean moneyrequired;

    //Identifier
    @Column(name = "rankingpoints")
    private int rankingpoints;
    
    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Min(1)
    @Max(5)
    @Column(name = "priority")
    private int priority;
    
    //Enum
    public static enum NotificationCategory {
        Other, Workspace, Process, Organization
    }
    
    //Enum
    public static enum NotificationStatus {
        New, Pending, WIP, Rejected, Completed
    }
    
    //Identifier
    /**
     * Enumerated internal class for Type field.
     *
     * @see pl.kwmm.wis.ejb.endpoint.NotificationEndpoint#setStatusAwaiting(pl.kwmm.wis.model.Notification) 
     * @see pl.kwmm.wis.ejb.endpoint.NotificationEndpoint#setStatusInImplementation(pl.kwmm.wis.model.Notification) 
     * @see pl.kwmm.wis.ejb.endpoint.NotificationEndpoint#setStatusRejected(pl.kwmm.wis.model.Notification) 
     * @see pl.kwmm.wis.ejb.endpoint.NotificationEndpoint#completeNotification(pl.kwmm.wis.model.Notification) 
     *
     * @version 1.0
     * @since 2019-06-04
     */
    //Enum
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{constraint.string.length.notnull}")
    @Column(name = "category")
    private NotificationCategory category;

    //Enum
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{constraint.string.length.notnull}")
    @Column(name = "status")
    private NotificationStatus status;

    //Relation
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "vote_voter",
            joinColumns = @JoinColumn(name = "vote_id"),
            inverseJoinColumns = @JoinColumn(name = "voter_id")
    )
    private List<Employee> voters = new ArrayList<>();

    //Relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //**************METHODS
    /**
     * PrePersist method for adding two fields (createdOn, createdBy)
     * automatically before each time Notification object is created into
     * database. For evidence of creation in future investigations.
     */
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        createdBy = ApplicationUtils.getUserName();
    }

    /**
     * PreUpdate method for adding two fields (updatedOn, updatedBy)
     * automatically before each time Notification object is changed in
     * database. For evidence of creation in future investigations.
     */
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = ApplicationUtils.getUserName();
    }

    /**
     * Method for adding Employee to the instance and in other way after adding to the Notification addint to voter list. 
     * @param voter - parameter for this method only. Needed from calling source. Employee instance.
     */
    public void addVoter(Employee voter) {
        voters.add(voter);
        voter.getVotes().add(this);
    }

    //**************ONLY GETTERS FOR FIELDS THAT SHOULDN'T BE CHANGED
    public long getNotification_id() {
        return notification_id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public long getVersion() {
        return version;
    }
    
    //**************STANDARD GETTERS/SETTERS

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

    public boolean isMoneyrequired() {
        return moneyrequired;
    }

    public void setMoneyrequired(boolean moneyrequired) {
        this.moneyrequired = moneyrequired;
    }

    public int getRankingpoints() {
        return rankingpoints;
    }

    public void setRankingpoints(int rankingpoints) {
        this.rankingpoints = rankingpoints;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public NotificationCategory getCategory() {
        return category;
    }

    public void setCategory(NotificationCategory category) {
        this.category = category;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public List<Employee> getVoters() {
        return voters;
    }

    public void setVoters(List<Employee> voters) {
        this.voters = voters;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
