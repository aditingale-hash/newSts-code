package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	@Query("select u from User u where u.username=?1")
	User findByUsername(String username);

	static List<User> findByQuestionsId(Long qid) {
		// TODO Auto-generated method stub
		return null;
	}

}
