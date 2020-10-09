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

import com.bah.customer.domain.Registration;
import com.bah.customer.service.RegistrationService;

@RestController
@RequestMapping("/registrations")
public class RegistrationAPI {

	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping
	public Iterable<Registration> getAll(){
		return registrationService.getAllRegistrations();
	}
	
	@GetMapping("/{registrationId}")
	public Registration getRegistrationById(@PathVariable("registrationId") long id){
		return registrationService.getRegistrationByID(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addRegistration(@RequestBody Registration newRegistration, UriComponentsBuilder uri){
		if (newRegistration.getId()!=0
			|| newRegistration.getCode()==null
			|| newRegistration.getDescription()==null
			|| newRegistration.getTitle() == null) {//Reject - we'll assign the registration id
			return ResponseEntity.badRequest().build();
		}
		newRegistration=registrationService.addandUpdateRegistration(newRegistration);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getId()).toUri();
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{registrationId}")
	public ResponseEntity<?> putRegistration(@RequestBody Registration newRegistration, @PathVariable("registrationId")long registrationId){
		if (newRegistration.getId()!= registrationId
			|| newRegistration.getCode()==null
			|| newRegistration.getDescription()==null
			|| newRegistration.getTitle() == null) {//Reject - we'll assign the registration id
			return ResponseEntity.badRequest().build();
		}
		newRegistration=registrationService.addandUpdateRegistration(newRegistration);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{registrationId}")
	public ResponseEntity<?> deleteRegistration(@PathVariable("registrationId")long registrationId){
		Registration newRegistration = registrationService.getRegistrationByID(registrationId);
		if (newRegistration == null) {//Reject - we'll assign the registration id
			return ResponseEntity.badRequest().build();
		}
		registrationService.removeRegistrationById(registrationId);
		return ResponseEntity.ok().build();
	}

}
