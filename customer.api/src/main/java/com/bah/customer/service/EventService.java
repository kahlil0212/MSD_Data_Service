package com.bah.customer.service;

import com.bah.customer.domain.Event;

public interface EventService {
	
	Iterable<Event> getAllEvents();
	
	Event getEventByID(long eventId);
	
	Event getEventByTitle(String eventTitle);

	Event addandUpdateEvent(Event event);
	
	void removeEventById(long eventId);

}
