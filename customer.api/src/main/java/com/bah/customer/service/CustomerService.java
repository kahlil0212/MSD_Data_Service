package com.bah.customer.service;

import com.bah.customer.domain.Customer;

public interface CustomerService {
	
	Iterable<Customer> getAllCustomers();
	
	Customer getCustomerByID(long custId);

	void addCustomer(Customer customer);
	
	void removeCustomerById(long custId);
	
	

}
