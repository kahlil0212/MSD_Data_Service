package com.bah.customer.persistence;

import org.springframework.data.repository.CrudRepository;

import com.bah.customer.domain.Event;

//@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

}
