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

import com.bah.customer.domain.Event;
import com.bah.customer.service.EventService;

@RestController
@RequestMapping("/events")
public class EventAPI {

	@Autowired
	private EventService eventService;
	
	@GetMapping
	public Iterable<Event> getAll(){
		return eventService.getAllEvents();
	}
	
	@GetMapping("/{eventId}")
	public Event getEventById(@PathVariable("eventId") long id){
		return eventService.getEventByID(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event newEvent, UriComponentsBuilder uri){
		if (newEvent.getId()!=0
			|| newEvent.getCode()==null
			|| newEvent.getDescription()==null
			|| newEvent.getTitle() == null) {//Reject - we'll assign the event id
			return ResponseEntity.badRequest().build();
		}
		newEvent=eventService.addandUpdateEvent(newEvent);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvent.getId()).toUri();
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping
	public ResponseEntity<?> putEvent(@RequestBody Event newEvent){
		if (newEvent.getCode()==null
			|| newEvent.getDescription()==null
			|| newEvent.getTitle() == null) {//Reject - we'll assign the event id
			return ResponseEntity.badRequest().build();
		}
		newEvent=eventService.addandUpdateEvent(newEvent);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable("eventId")long eventId){
		Event newEvent = eventService.getEventByID(eventId);
		if (newEvent == null) {//Reject - we'll assign the event id
			return ResponseEntity.badRequest().build();
		}
		eventService.removeEventById(eventId);
		return ResponseEntity.ok().build();
	}
}
