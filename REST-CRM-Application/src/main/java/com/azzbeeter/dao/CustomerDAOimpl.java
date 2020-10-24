package com.azzbeeter.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.azzbeeter.entity.Customer;

@Repository
public class CustomerDAOimpl implements CustomerDAO {

	
	//need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() 
	{
		//get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) 
	{
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int id) 
	{
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, id);
		return theCustomer;
	}

	@Override
	public Customer deleteCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, id);
		currentSession.delete(theCustomer);
		return theCustomer;
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) 
	{
		Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else 
        {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        
        return customers;
        // return the results        
    }
	
	
}
