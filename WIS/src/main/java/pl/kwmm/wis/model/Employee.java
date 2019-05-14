package pl.kwmm.wis.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findById", query = "SELECT e FROM Employee e WHERE e.id = :id"),
    @NamedQuery(name = "Employee.findByFirstname", query = "SELECT e FROM Employee e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Employee.findByLastname", query = "SELECT e FROM Employee e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Employee.findByEmployeenumber", query = "SELECT e FROM Employee e WHERE e.employeenumber = :employeenumber"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByNotificationpoints", query = "SELECT e FROM Employee e WHERE e.notificationpoints = :notificationpoints")})
@SecondaryTable(name = "PersonalData")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    //Account Data (Main)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min = 1, max = 30)
    @Column(name = "login", nullable = false)
    private String login;

    @Size(min = 1, max = 30)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "satus", nullable = false)
    private boolean status;

    @Size(min = 1, max = 50)
    @Column(name = "type", nullable = false)
    private String type;

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

    public Employee() {
    }

    public Employee(String login, String password, boolean status, String firstname, String lastname, String employeenumber, String email, Integer notificationpoints, String type) {
        this.login = login;
        this.password = password;
        this.status = status;
        this.firstname = firstname;
        this.lastname = lastname;
        this.employeenumber = employeenumber;
        this.email = email;
        this.notificationpoints = notificationpoints;
        this.type = type;
    }

    public Integer getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
