package com.bah.customer.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bah.customer.domain.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

}

