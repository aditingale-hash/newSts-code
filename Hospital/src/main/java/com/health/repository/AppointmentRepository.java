package com.health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.model.AppointmentForm;

public interface AppointmentRepository extends JpaRepository<AppointmentForm, Long> {

	List<AppointmentForm> findByDoctorId(Long did);

}
