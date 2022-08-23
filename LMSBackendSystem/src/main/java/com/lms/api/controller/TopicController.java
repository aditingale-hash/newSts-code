package com.lms.api.controller;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.QuestionDto;
import com.lms.api.dto.StatsTopicDto;
import com.lms.api.dto.TopicDto;
import com.lms.api.model.Answer;
import com.lms.api.model.Questions;
import com.lms.api.model.Topic;
import com.lms.api.model.User;
import com.lms.api.repository.AnswerRepository;
import com.lms.api.repository.QuestionRepository;
import com.lms.api.repository.TopicRepository;
import com.lms.api.repository.UserRepository;

@RestController
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//post topics in db
	
	@PostMapping("/topic")
	public Topic AddTopic(@RequestBody Topic topic) {
		return topicRepository.save(topic);
		
	}

	//Assign questions to topics******
	
	@PostMapping("/topic/question/{tid}/{qid}")
	public Topic assignQuestionToTopic(@PathVariable("tid") Long tid , @PathVariable("qid")Long qid) {
		
		//fetch topic by topic id
		Topic topic=topicRepository.getById(tid);
		
		//get questions fom topics and add it in list of questions
		List<Questions> questions=topic.getQuestions();
		
		//fetch question by question id
		Questions question=questionRepository.getById(qid);
		
		//add question id 
		questions.add(question);
		
		//added questions to topic id
		topic.setQuestions(questions);
		
		
		return topicRepository.save(topic);
			
	}
	

	/*
	 *get all topics with pagination
	 * 
	 * */


	  @GetMapping("/topics")
	  public List<PaginationDto> getAllTopics(
	  
	  @RequestParam(name="page",required = false,defaultValue = "0") Integer page,
	  
	  @RequestParam(name="size",required = false,defaultValue = "2") Integer size){
	  
		  Pageable pageable=PageRequest.of(page, size);
	  
		  PaginationDto dto=new PaginationDto();
		  
	 List<Topic> list= topicRepository.findAll(pageable).getContent();
	 
	     List<Questions> question=new ArrayList<>();
	     
	     List<PaginationDto> quelist=new ArrayList<>();
	     
	     for(Topic t:list) {
	    	 
	    	 dto.setId(t.getId());
	    	 dto.setTopicName(t.getTopic());
	    	 question=t.getQuestions();
	         dto.setNumberofquestions(question.size());
	         quelist.add(dto);
	     }
	     
	     return quelist;
	  } 
	  
	  //get question according to topic id
	  
	  @GetMapping("/question/{tid}") 
	public TopicDto getAllTopics(@PathVariable("tid") Long tid)
	  {
			
			//fetch topic id
		   Topic topic=topicRepository.getById(tid);
			
		   
		   //fecth questions by topic id
			List<Questions>question=questionRepository.findByTopicId(tid);
			
			
			//List<Questions>listQuestions=new ArrayList<>();
			
			//make questiondto list
			List<QuestionDto>dtlist=new ArrayList<>();
			
			
			
			// dto1.setId(t.getId());
			//dto1.setTopic(t.getTopic());
			// 
			 //top.add(dto1);
			 //dto.setTopic(topic);
			
			//topic dto for topic
			 TopicDto dto1=new TopicDto();
			 
			 //sort question list which we gonna give to for loop
	 List<Questions>sorted=question.stream().sorted(Comparator.comparing(Questions::getDateofpost).reversed())
											.collect(Collectors.toList());
			 
		for(Questions q: sorted){

			QuestionDto dto=new QuestionDto();
				
              dto.setId(q.getId());
              dto.setText(q.getQuestion_text());
              dto.setUsername(q.getUsername());
              dto.setLikes(q.getLikes());
              dto.setNumberofanswer(q.getAnswer().size());
              dto.setPostdate(q.getDateofpost());
              dtlist.add(dto);
				
				
			}
		
		dto1.setId(topic.getId());
		dto1.setTopic(topic.getTopic());
		//add question dto list to topic dto list and return it
		dto1.setQuestions(dtlist);
		
		return dto1;
		
}
	  
	  @GetMapping("/stats/topic")
	  public StatsTopicDto getstatsTopic() {
		  
		  List<Questions>  question=questionRepository.findAll();
		  List<Answer> answer=answerRepository.findAll();
		  List<Topic> topic=topicRepository.findAll();
		 // List<User>user=userRepository.findAll();
		  
		  Set<String> h=new HashSet<>();
		  answer.stream().forEach(a->{
			 h.add(a.getUsername());     
		  });
		    question.stream().forEach(q->{
			  h.add(q.getUsername());
			  });
		  

		  
		  StatsTopicDto dto =new StatsTopicDto();
		  
		  
			dto.setTotalNumberOfQuestions(question.size());
			
			dto.setTotalNumberOfAnswers(answer.size());
		
			dto.setTotalNumberOfTopics(topic.size());
	
			dto.setTotalNumberOfUsers(h.size());
			
			
		return dto;
			
	  }
	  
	  @GetMapping("/user")
	  public List<User> user() {
		 List<Questions>  question=questionRepository.findAll();
		  List<Answer> answer=answerRepository.findAll();
		 // List<Topic> topic=topicRepository.findAll();
		  List<User>user=userRepository.findAll();
		  
		  List<String> h=new ArrayList<>();
		  answer.stream().forEach(a->{
			 h.add(a.getUsername());     
		  });
		    question.stream().forEach(q->{
			  h.add(q.getUsername());
			  });
		  
  List<User> diff=user.stream().distinct().filter(e->!(h.contains(e.getUsername()))).collect(Collectors.toList());
		  return diff;
	  }
	  
	  @PutMapping("/answer/update_likes/{aid}")
	  public Answer UpdateAnswer(@PathVariable("aid")Long aid) {
		  Answer ans=answerRepository.getById(aid);
		 
			  int  i = (ans.getLikes())+1; 
			  
		       ans.setLikes(i);
		  
		
		  return answerRepository.save(ans);
		   
	  }
	  
	  @PutMapping("/question/update_likes/{qid}")
	  public Questions Updatequestion(@PathVariable("qid")Long qid) {
		  Questions que=questionRepository.getById(qid);
		  
			 int  i = (que.getLikes())+1;
			  que.setLikes(i);
		  
		  return questionRepository.save(que);
		  
		  
		  
	  }
	  
	  @GetMapping("/question/likes/{qid}")
	  public int totallikes(@PathVariable("qid") Long qid){
		  
		  Questions q= questionRepository.getById(qid);
		  
		  int i =q.getLikes();
		  
		  return i;
		  
		  
	  }
		/*
		 * @GetMapping("/question/{tid}") public List<Answer>
		 * getbyquestionbyTopicId(@PathVariable("tid") Long tid) {
		 * List<Answer>answer=answerRepository.findByTopicId(tid);
		 * 
		 * return answer; }
		 */
	  
	  //user which have atleast posted one question or answer
	  @GetMapping("/user/list")
	  public List<User> username() {
		 List<Questions>  question=questionRepository.findAll();
		  List<Answer> answer=answerRepository.findAll();
		 // List<Topic> topic=topicRepository.findAll();
		  List<User>user=userRepository.findAll();
		  
		  List<String> h=new ArrayList<>();
		  answer.stream().forEach(a->{
			 h.add(a.getUsername());     
		  });
		    question.stream().forEach(q->{
			  h.add(q.getUsername());
			  });
		  
  List<User> diff=user.stream().distinct().filter(e->h.contains(e.getUsername())).collect(Collectors.toList());
		  return diff;
	  }
	  
}
