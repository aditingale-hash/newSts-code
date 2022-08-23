
package com.lms.api.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.api.model.Topic;

public interface TopicRepository extends JpaRepository<Topic,Long> {

	@Query("select t from Topic t where t.id=?1")
	List<Topic> findByTopicId(Long tid);
	/*
	 *  List<Topic> findByTopicId();
	 */

	List<Topic> findByQuestionsId(Long qid);

	
	
}
