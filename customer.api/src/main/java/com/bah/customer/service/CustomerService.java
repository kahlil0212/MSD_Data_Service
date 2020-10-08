package com.bah.customer.service;

import com.bah.customer.domain.Customer;

public interface CustomerService {
	
	Iterable<Customer> getAllCustomers();
	
	Customer getCustomerByID(long custId);

	Customer addandUpdateCustomer(Customer customer);
	
	void removeCustomerById(long custId);
	
	

}
