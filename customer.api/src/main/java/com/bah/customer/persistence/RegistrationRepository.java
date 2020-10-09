package com.bah.customer.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bah.customer.domain.Registration;

//@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Long> {

}
