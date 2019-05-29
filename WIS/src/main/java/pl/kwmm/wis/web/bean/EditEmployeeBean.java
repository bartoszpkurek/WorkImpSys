/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kwmm.wis.web.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.kwmm.wis.model.Employee;
import pl.kwmm.wis.web.controller.EmployeeController;

/**
 *
 * @author Warsztat
 */
@Named
@RequestScoped
public class EditEmployeeBean {

    public EditEmployeeBean() {
    }

    @Inject
    private EmployeeController empCtrl;

    @PostConstruct
    private void init() {
        employee = empCtrl.getCurrentAccount();
    }

    private Employee employee = new Employee();

    public Employee getEmployee() {
        return employee;
    }

    public String saveEmployeeAfterEdit() {
        return empCtrl.saveEmployeeAfterEdit(employee);
    }

}
