package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Questions;

public interface QuestionRepository extends JpaRepository<Questions, Long>{

	List<Questions> findByTopicId(Long id);

	//@Query("select t from Quetions q join q.topic t where t.id=?1")
	//@Query("select q from Quetions q join q.topic t where t.id=?1")
	List<Questions> getByTopicId(Long tid);
	
	@Query("select q from Questions q where q.id=?1")
	List<Questions> findByQuestionsId(Long qid);

	//@Query("select t from Topic r where r.course=?1")
	//List<Questions> findByTopicId(Long tid);

}
