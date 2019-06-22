package pl.kwmm.wis.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import pl.kwmm.wis.web.utils.ApplicationUtils;

/**
 * Entity class for General Employee object. Divided into two tables: EMPLOYEE &
 * PERSONALDATA. EMPLOYEE table contains data: Utils Fields/Checkers
 * Fields/Identifiers Fields PERSONALDATA table contains data: Relations/Identifiers
 *
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-01
 */
@Entity
@Table(name = "employee")
@SecondaryTable(name = "PersonalData")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    //**************FIELDS
    /* 
        Utils fields for EMPLOYEE table:
        For checking unique id/versions/created date/created by user 
     */
    //Utils
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", updatable = false)
    private long employee_id;

    //Utils
    @Column(name = "created_on")
    @NotNull
    private LocalDateTime createdOn;

    //Utils (should be always Anonymous)
    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    //Utils
    @Version
    private long version;

    /* 
        Specific account fields for EMPLOYEE table:
        Fields for account identifications: login/password/type/lasttype/status      
        Fields for relationships votes/notifications
     */
    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Size(min = 3, max = 30, message = "{constraint.string.length.login.notinrange}")
    @Pattern(regexp = "^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "{constraint.string.length.login.charnotallowed}")
    @Column(name = "login", unique = true)
    private String login;

    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Size(min = 8, message = "{constraint.string.length.password.notinrange}")
    @Column(name = "password")
    private String password;

    //Identifier
    /**
     * Enumerated internal class for Type field.
     *
     * @see pl.kwmm.wis.web.bean.AddEmployeeConfirmBean#register()
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#registerEscalatedAccount(pl.kwmm.wis.model.Employee)
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#disableEmployee(pl.kwmm.wis.model.Employee)
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#setAdminRole(pl.kwmm.wis.model.Employee)
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#setEmployeeRole(pl.kwmm.wis.model.Employee)
     * @see pl.kwmm.wis.ejb.endpoint.EmployeeEndpoint#setImpTeamRole(pl.kwmm.wis.model.Employee)
     *
     * @version 1.0
     * @since 2019-06-01
     */
    public static enum EmployeeType {
        Disabled, Employee, ImpTeam, Admin
    }

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{constraint.string.length.notnull}")
    @Column(name = "type")
    private EmployeeType type;

    //Identifier
    @Enumerated(EnumType.STRING)
    @Column(name = "lasttype")
    private EmployeeType lasttype;

    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Column(name = "status")
    private boolean status;
    
     //Relation
    @ManyToMany(mappedBy = "voters")
    private List<Notification> votes = new ArrayList<>();
    
    //Relation
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Notification> notifications;

    /* 
        Utils fields for PERSONALDATA table:
        Checking updatedOn/updatedBy user.
        Fields for identification Employee: firstname/lastname/employeenumber/email/notificationpoints
     */
    //Checker (can be null on creation)
    @Column(name = "updated_on", table = "PersonalData")
    private LocalDateTime updatedOn;

    //Checker (can be null on creation)
    @Column(name = "updated_by", table = "PersonalData")
    private String updatedBy;

    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Size(min=1, max = 100, message = "{constraint.string.length.toolong}")
    @Pattern(regexp = "^[a-zA-Z ,.'-]+$", message = "{constraint.string.length.firstlastname}")
    @Column(table = "PersonalData", name = "firstname")
    private String firstname;

    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Size(min=1, max = 100, message = "{constraint.string.length.toolong}")
    @Pattern(regexp = "^[a-zA-Z ,.'-]+$", message = "{constraint.string.length.firstlastname}")
    @Column(table = "PersonalData", name = "lastname")
    private String lastname;

    //Identifier
    @NotNull(message = "{constraint.string.length.notnull}")
    @Size(max = 20, message = "{constraint.string.length.toolong}")
    @Column(table = "PersonalData", name = "employeenumber")
    private String employeenumber;

    //Identifier
    @Column(table = "PersonalData", name = "email")
    @Size(max = 100, message = "{constraint.string.length.toolong}")
    @Email(message = "{javax.validation.constraints.Email.message}")
    private String email;

    //Identifier
    @Column(table = "PersonalData", name = "notificationpoints")
    private int notificationpoints;

    //**************METHODS
    /**
     * PrePersist method for adding two fields (createdOn, createdBy) automatically before each time Employee object is created into database.
     * For evidence of creation in future investigations.
     */
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        createdBy = ApplicationUtils.getUserName();
    }

    /**
     * PreUpdate method for adding two fields (updatedOn, updatedBy) automatically before each time Employee object is changed in database.
     * For evidence of creation in future investigations.
     */
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = ApplicationUtils.getUserName();
    }
    
    //**************ONLY GETTERS FOR FIELDS THAT SHOULDN'T BE CHANGED
    public long getEmployee_id() {
        return employee_id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }
      
    public long getVersion() {
        return version;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
    
    //**************STANDARD GETTERS/SETTERS
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public EmployeeType getLasttype() {
        return lasttype;
    }

    public void setLasttype(EmployeeType lasttype) {
        this.lasttype = lasttype;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Notification> getVotes() {
        return votes;
    }

    public void setVotes(List<Notification> votes) {
        this.votes = votes;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNotificationpoints() {
        return notificationpoints;
    }

    public void setNotificationpoints(int notificationpoints) {
        this.notificationpoints = notificationpoints;
    }
}
