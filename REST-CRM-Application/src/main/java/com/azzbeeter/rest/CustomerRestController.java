package com.azzbeeter.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azzbeeter.entity.Customer;
import com.azzbeeter.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController 
{
	//Autowire customer service
	@Autowired
	private CustomerService customerService;
	
	//Add mapping for GetCustomers
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId)
	{
		if(customerId>customerService.getCustomers().size() || customerId<0)
		{
			throw new CustomerNotFoundException("Pass a valid ID fool..");
		}
		else
		{
			Customer theCustomer = customerService.getCustomer(customerId);
			return theCustomer;
		}
	}
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer)
	{
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public int deleteCustomer(@PathVariable int customerId)
	{
		if(customerId>customerService.getCustomers().size() || customerId<0)
		{
			throw new CustomerNotFoundException("Pass a valid ID fool..");
		}
		else
		{
			customerService.deleteCustomer(customerId);
			return customerId;
		}
	}
}
