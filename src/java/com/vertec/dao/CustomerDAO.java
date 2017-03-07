/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vertec.dao;

import com.vertec.hibe.model.Customer;
import java.util.List;

/**
 *
 * @author User
 */
public interface CustomerDAO {
    
    public abstract String saveCustomer(Customer customer);
    
    public abstract List<Customer> getListOfCustomer();
    
    public abstract Customer viewCustomer(int customerId);
    
    public abstract String updateCustomer(Customer customer);
    
    public abstract String removeCustomer(int customerId);
    
}
