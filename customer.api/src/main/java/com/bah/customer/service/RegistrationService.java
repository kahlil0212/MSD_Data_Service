package com.bah.customer.service;

import com.bah.customer.domain.Registration;


public interface RegistrationService {
	
	Iterable<Registration> getAllRegistrations();
	
	Registration getRegistrationByID(long registrationId);

	Registration addandUpdateRegistration(Registration registration);
	
	void removeRegistrationById(long registrationId);

}
