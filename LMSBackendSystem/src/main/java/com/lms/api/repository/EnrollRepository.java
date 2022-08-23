package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.api.model.Course;
import com.lms.api.model.Enroll;

public interface EnrollRepository extends JpaRepository<Enroll, Long>{

	List<Course> findByUserId(Long userid);

}
