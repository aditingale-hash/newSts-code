package com.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	Doctor findBySpeclizationId(Long sid);

}
