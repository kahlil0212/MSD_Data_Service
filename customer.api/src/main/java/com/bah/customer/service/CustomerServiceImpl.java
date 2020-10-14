package com.bah.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.customer.domain.Customer;
import com.bah.customer.persistence.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Iterable<Customer> getAllCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerByID(long custId) {

		return customerRepository.findById(custId).get();
	}
	
	@Override
	public Customer getCustomerByName(String custName) {
		Iterable<Customer> customerList = this.getAllCustomers();
		
		
		for(Customer customer: customerList) {
			if(customer.getName().equals(custName))
				return customer;
		}
		return null;
	}

	@Override
	public Customer addandUpdateCustomer(Customer customer) {
		
		return customerRepository.save(customer);
		
	}

	@Override
	public void removeCustomerById(long custId) {
		customerRepository.deleteById(custId);
		
	}

}
