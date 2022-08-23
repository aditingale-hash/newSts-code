package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.health.model.AppointmentForm;
import com.health.model.Doctor;
import com.health.model.Precription;
import com.health.repository.AppointmentRepository;
import com.health.repository.DoctorRepository;


@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class AppointmentController {
	
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private DoctorRepository  doctorRepository;
	
	@PostMapping("/appointment")
	public AppointmentForm PostAppointemnt(@RequestBody AppointmentForm form){
		return appointmentRepository.save(form);
	}
	
	@GetMapping("/getappointment")
	public List<AppointmentForm> getAppointment() {
		
		return appointmentRepository.findAll();
	}

	/*
	 * @PostMapping("/appointment/{username}") public AppointmentForm
	 * postAppointmentByUsername(@PathVariable("username") String username,
	 * 
	 * @RequestBody Precription precription) {
	 * 
	 * AppointmentForm username=appointmentRepository
	 * form.setSpeclization(speclization); return appointmentRepository.save(form);
	 * 
	 */
	//post appointment by doctor id;
	@PostMapping("/appointment/{did}")
	public AppointmentForm postAppointmentByDoctorID(@PathVariable("did") Long did,
			@RequestBody AppointmentForm form) {
		
	         Doctor doctor=doctorRepository.getById(did);
		form.setDoctor(doctor);
				return appointmentRepository.save(form);
			
	}
	
	//get appointment by doctor id 
	@GetMapping("/appointment/{did}")
	public List<AppointmentForm> getappointmentbydoctorID(@PathVariable("did")Long did){
		
		List<AppointmentForm> appointment=appointmentRepository.findByDoctorId(did);
		
		return appointment;
	}
	@GetMapping("/appointment")
	public List<AppointmentForm> getappointment(){
		
	
		
		return appointmentRepository.findAll();
	}
	
}
