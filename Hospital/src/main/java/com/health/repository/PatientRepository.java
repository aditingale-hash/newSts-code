package com.health.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.health.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long>{

	//@Query("select p from Patient p.id where p.id =?1")
  //Precription findByPatientId(Long pid);

	

}
