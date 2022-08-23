package com.health.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;




@Entity
public class Speclization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToOne
	private AppointmentForm appointmentForm;
	

	public AppointmentForm getAppointmentForm() {
		return appointmentForm;
	}
	public void setAppointmentForm(AppointmentForm appointmentForm) {
		this.appointmentForm = appointmentForm;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
