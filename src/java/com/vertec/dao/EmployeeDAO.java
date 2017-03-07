/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.dao;

import com.vertec.hibe.model.Employee;
import java.util.List;

/**
 *
 * @author User
 */
public interface EmployeeDAO {
    
    public abstract String saveEmployee(Employee employee);
    
    public abstract List<Employee> getListOfEmployee();
    
    public abstract Employee viewEmployee(int employeeId);
    
    public abstract String updateEmployee(Employee employee);
    
    public abstract String removeEmployee(int empId);
    
}
