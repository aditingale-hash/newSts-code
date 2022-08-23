package com.lms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	
	List<Answer> findByQuestionsId(Long qid);

	//  @Query("select v from Video v join v.module m join m.course c where c.id=?1")
	/*
	 * @Query("select a from Answer a join a.questions q join q.topic t where t.id=?1 "
	 * ) List<Answer> findByTopicId(Long tid);
	 */

}
