package com.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.health.model.Precription;

public interface PrecriptionRepository extends JpaRepository<Precription, Long>{
		
	@Query("select u from Precription u where u.username=?1")
	Precription findByUsername(String username);


}
