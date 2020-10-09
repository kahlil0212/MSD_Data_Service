package com.bah.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bah.customer.domain.Event;
import com.bah.customer.persistence.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public Iterable<Event> getAllEvents() {
		
		return eventRepository.findAll();
	}

	@Override
	public Event getEventByID(long eventId) {
		return eventRepository.findById(eventId).get();
	}

	@Override
	public Event addandUpdateEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public void removeEventById(long eventId) {
		eventRepository.deleteById(eventId);

	}

	@Override
	public Event getEventByTitle(String eventTitle) {
		Iterable<Event> eventList = this.getAllEvents();
		
		for(Event event: eventList) {
			if(event.getTitle().equals(eventTitle))
				return event;
		}
		return null;
	}

}
