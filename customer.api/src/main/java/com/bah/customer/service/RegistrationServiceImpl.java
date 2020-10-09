package com.bah.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.customer.domain.Registration;
import com.bah.customer.persistence.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;

	@Override
	public Iterable<Registration> getAllRegistrations() {
		return registrationRepository.findAll();
	}

	@Override
	public Registration getRegistrationByID(long registrationId) {
		return registrationRepository.findById(registrationId).get();
	}

	@Override
	public Registration addandUpdateRegistration(Registration registration) {
		return registrationRepository.save(registration);
	}

	@Override
	public void removeRegistrationById(long registrationId) {
		registrationRepository.deleteById(registrationId);

	}

}
