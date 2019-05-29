package pl.kwmm.wis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
@SecondaryTable(name = "PersonalData")

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    //Account Data (Main)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private long employee_id;

    @Size(min = 1, max = 30)
    @Column(name = "login", nullable = false)
    private String login;

    @Size(min = 1, max = 30)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(min = 1, max = 50)
    @Column(name = "type", nullable = false)
    private String type;

    @Size(min = 1, max = 50)
    @Column(name = "lasttype", nullable = true)
    private String lasttype;

    @Column(name = "status", nullable = false)
    private boolean status;

    //Personal Data (Secondary)
    @Size(min = 1, max = 50)
    @Column(table = "PersonalData", name = "firstname", nullable = false)
    private String firstname;

    @Size(min = 1, max = 50)
    @Column(table = "PersonalData", name = "lastname", nullable = false)
    private String lastname;

    @Size(min = 1, max = 30)
    @Column(table = "PersonalData", name = "employeenumber", nullable = false)
    private String employeenumber;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(min = 1, max = 30)
    @Column(table = "PersonalData", name = "email", nullable = false)
    private String email;

    @Column(table = "PersonalData", name = "notificationpoints")
    private Integer notificationpoints;

    @ManyToMany(mappedBy = "tags")
    private List<Notification> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Notification> notifications;

    public Employee() {
    }

    public Employee(long employee_id, String login, String password, String type, String lasttype, boolean status, String firstname, String lastname, String employeenumber, String email, Integer notificationpoints, List<Notification> notifications) {
        this.employee_id = employee_id;
        this.login = login;
        this.password = password;
        this.type = type;
        this.lasttype = lasttype;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.employeenumber = employeenumber;
        this.email = email;
        this.notificationpoints = notificationpoints;
        this.notifications = notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }



    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLasttype() {
        return lasttype;
    }

    public void setLasttype(String lasttype) {
        this.lasttype = lasttype;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public Integer getNotificationpoints() {
        return notificationpoints;
    }

    public void setNotificationpoints(Integer notificationpoints) {
        this.notificationpoints = notificationpoints;
    }

    public List<Notification> getPosts() {
        return posts;
    }

    public void setPosts(List<Notification> posts) {
        this.posts = posts;
    }

}
