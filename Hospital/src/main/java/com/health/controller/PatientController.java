package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.health.model.Patient;
import com.health.repository.PatientRepository;


@RestController
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	
	@PostMapping("/patient")
	public Patient PostPrecription(@RequestBody Patient patient){
		return patientRepository.save(patient);
	}
	
	@GetMapping("/patient")
	public List<Patient> getAllPatient() {
		
		return patientRepository.findAll();
	}
}
