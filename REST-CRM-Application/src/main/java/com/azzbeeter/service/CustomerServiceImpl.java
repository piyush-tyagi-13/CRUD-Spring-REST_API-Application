package com.azzbeeter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azzbeeter.dao.CustomerDAO;
import com.azzbeeter.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	//need to inject customerDAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() 
	{
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) 
	{
		customerDAO.saveCustomer(theCustomer);
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) 
	{
		Customer theCustomer = customerDAO.getCustomer(id);
		return theCustomer;
	}

	@Override
	@Transactional
	public Customer deleteCustomer(int id) {
		Customer theCustomer = customerDAO.deleteCustomer(id);
		return theCustomer;
	}
	
	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) 
	{
		return customerDAO.searchCustomers(theSearchName);
	}
}
