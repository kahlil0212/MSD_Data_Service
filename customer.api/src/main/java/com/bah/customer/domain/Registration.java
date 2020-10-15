package com.bah.customer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTRATIONS")
public class Registration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
//	@ManyToOne
//	private Event event;
	
	@Column(name = "EVENT_ID")
	long event_id;
	

//	@ManyToOne
//	private Customer customer;
	
	@Column(name = "CUSTOMER_ID")
	long customer_id;
	
	private Date registration_date;
	
	private String notes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", event_id=" + event_id + ", customer_id=" + customer_id
				+ ", registrationDate=" + registration_date + ", notes=" + notes + "]";
	}
	
	
}
