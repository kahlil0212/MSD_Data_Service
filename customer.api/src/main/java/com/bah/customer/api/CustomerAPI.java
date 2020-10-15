package com.bah.customer.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.customer.domain.Customer;
import com.bah.customer.service.CustomerService;


@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public Iterable<Customer> getAll(){
		System.out.println(customerService.getAllCustomers());
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/id/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long id){
		return customerService.getCustomerByID(id);
	}
	
	@GetMapping("/byname/{customerName}")
	public ResponseEntity<?> getCustomerByName(@PathVariable("customerName") String customerName) {
		Customer customer = customerService.getCustomerByName(customerName);
		
		if(customer == null) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(customer);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri){
		
		System.out.println("New customer: " + newCustomer);
		if (newCustomer.getId()!=0
			|| newCustomer.getName()==null
			|| newCustomer.getEmail()==null) {//Reject - we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		newCustomer=customerService.addandUpdateCustomer(newCustomer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> putCustomer(@PathVariable long id, 
			@RequestBody Customer newCustomer){
		if (newCustomer.getName()==null
			|| newCustomer.getEmail()==null) {//Reject - we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		newCustomer=customerService.addandUpdateCustomer(newCustomer);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/id/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId")long customerId){
		Customer newCustomer = customerService.getCustomerByID(customerId);
		if (newCustomer == null) {//Reject - we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		customerService.removeCustomerById(customerId);
		return ResponseEntity.ok().build();
	}
	
}
