package pl.kwmm.wis.web.utils;

import java.util.Random;
import pl.kwmm.wis.model.Employee;

/**
 * Java class for Utils on Employee Data. Contains static methods. Contains
 * changing account data method. Contains randomizing password method.
 *
 * @author Bartosz Kurek
 * @version 1.0
 * @since 2019-06-20
 */
public class EmployeeUtils {

    /**
     * Static method for changing fields of Entity, but only four related to
     * user data. First checks condition if parameters aren't null.
     *
     * @param employee - Employee data from changing form
     * @param employeeAcc - Employee fields
     */
    public static void employeeEdit(Employee employee, Employee employeeAcc) {

        if (null == employee || null == employeeAcc) {
            return;
        }

        employeeAcc.setEmail(employee.getEmail());
        employeeAcc.setEmployeenumber(employee.getEmployeenumber());
        employeeAcc.setFirstname(employee.getFirstname());
        employeeAcc.setLastname(employee.getLastname());
    }

    /**
     * Static method for randomize password.
     * Uses String "ChangeMe!" and add Random int
     * 
     * @return Password
     */
    public static String passwordChange() {
        Random rand = new Random();
        int n = rand.nextInt(99999999);
        String passComb = "ChangeMe!" + n;
        return passComb;
    }
}
