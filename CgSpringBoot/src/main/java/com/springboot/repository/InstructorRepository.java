package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

	
	@Query("select i from Instructor i join i.course c where c.id=?1")
	List<Instructor> findByCourseId(Long cid);

	
	@Query("select MIN(i.salary) as max, MAX(i.salary) as sal, COUNT (i.id) as cnt from Instructor i") 
	List<Double[]> getMaxSalary();

}
