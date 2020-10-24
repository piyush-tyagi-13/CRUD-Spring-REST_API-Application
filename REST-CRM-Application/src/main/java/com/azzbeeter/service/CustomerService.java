package com.azzbeeter.service;

import java.util.List;

import com.azzbeeter.entity.Customer;

public interface CustomerService 
{
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int id);

	public Customer deleteCustomer(int id);

	public List<Customer> searchCustomers(String theSearchName);

}
