package com.bah.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.customer.domain.Customer;
import com.bah.customer.persistence.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository custRepo;

	@Override
	public Iterable<Customer> getAllCustomers() {
		
		return custRepo.findAll();
	}

	@Override
	public Customer getCustomerByID(long custId) {

		return custRepo.findById(custId).get();
	}

	@Override
	public Customer addandUpdateCustomer(Customer customer) {
		
		return custRepo.save(customer);
		
	}

	@Override
	public void removeCustomerById(long custId) {
		custRepo.deleteById(custId);
		
	}

}
