/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.web.utils;

import java.util.Random;
import pl.kwmm.wis.model.Employee;

/**
 *
 * @author Warsztat
 */
public class EmployeeUtils {


    
    
    public static void employeeEdit(Employee employee, Employee employeeAcc) {

        if (null == employee || null == employeeAcc) return;

        employeeAcc.setEmail(employee.getEmail());
        employeeAcc.setEmployeenumber(employee.getEmployeenumber());
        employeeAcc.setFirstname(employee.getFirstname());
        employeeAcc.setLastname(employee.getLastname());
    }
    
    public static String passwordChange(Employee e){
        Random rand = new Random();
        int n = rand.nextInt(99999999);
       
        String passComb = "ChangeMe!" + n;
        e.setPassword(passComb);
        return passComb;
    }
}
