package com.bah.customer.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.bah.customer.persistence.CustomerRepository;


@RestController
@RequestMapping("/customers")
public class CustomerAPI {
	@Autowired
	CustomerRepository repo;
	
	@GetMapping
	public Iterable<Customer> getAll(){
		return repo.findAll();
	}
	
	@GetMapping("/{customerId}")
	public Optional<Customer> getCustomerById(@PathVariable("customerId") long id){
		return repo.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri){
		if (newCustomer.getId()!=0
			|| newCustomer.getName()==null
			|| newCustomer.getEmail()==null) {//Reject - we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		newCustomer=repo.save(newCustomer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable("customerId")long customerId){
		if (newCustomer.getId()!= customerId
			|| newCustomer.getName()==null
			|| newCustomer.getEmail()==null) {//Reject - we'll assign the customer id
			return ResponseEntity.badRequest().build();
		}
		newCustomer=repo.save(newCustomer);
		return ResponseEntity.ok().build();
	}
}
