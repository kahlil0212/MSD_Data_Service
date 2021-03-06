package com.bah.customer.api;

import java.net.URI;

import javax.websocket.server.PathParam;

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

import com.bah.customer.domain.Registration;
import com.bah.customer.service.CustomerService;
import com.bah.customer.service.EventService;
import com.bah.customer.service.RegistrationService;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationAPI {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private EventService eventService;

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public Iterable<Registration> getAll() {
		return registrationService.getAllRegistrations();
	}

	@GetMapping("/{registrationId}")
	public Registration getRegistrationById(@PathVariable("registrationId") long id) {
		return registrationService.getRegistrationByID(id);
	}

	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration registration, UriComponentsBuilder uri) {
		System.out.println("*** Registration "+ registration);
		if (registration.getId() != 0 && registration.getCustomer_id() != 0 && registration.getEvent_id() != 0
				|| registration.getNotes() == null || registration.getRegistration_date() == null) {
		// Reject -we'll assign the registration id
			
			return ResponseEntity.badRequest().build();
		}
		Registration newRegistration = registrationService.addandUpdateRegistration(registration);
		System.out.println("*** New Registration "+ newRegistration);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newRegistration.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}

	@PutMapping("{id}")
	public ResponseEntity<?> putRegistration(@PathVariable long id,
			@RequestBody Registration registration) {
		System.out.println("*** Put Registration "+ registration);
		if (registration.getCustomer_id() == 0
				&& registration.getEvent_id() == 0 || registration.getRegistration_date() == null
				|| registration.getNotes() == null
				|| registration.getRegistration_date() == null) {// Reject - we'll assign the registration id
			return ResponseEntity.badRequest().build();
		}
		Registration newRegistration = registrationService.addandUpdateRegistration(registration);
		
		System.out.println("*** New Put Registration "+ newRegistration);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{registrationId}")
	public ResponseEntity<?> deleteRegistration(@PathVariable long registrationId) {
		Registration newRegistration = registrationService.getRegistrationByID(registrationId);
		if (newRegistration == null) {// Reject - we'll assign the registration id
			return ResponseEntity.badRequest().build();
		}
		registrationService.removeRegistrationById(registrationId);
		return ResponseEntity.ok().build();
	}

}
