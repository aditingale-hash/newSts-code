package com.lms.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.AnswerDto;
import com.lms.api.model.Answer;
import com.lms.api.model.Questions;
import com.lms.api.repository.AnswerRepository;
import com.lms.api.repository.QuestionRepository;

@RestController
public class QuestionController {
	
@Autowired
private QuestionRepository questionRepository;

@Autowired
private AnswerRepository answerRepository;



	@PostMapping("/question")
	public Questions AddQuestion(@RequestBody Questions questions) {
		
		questions.setDateofpost(LocalDate.now());
		return questionRepository.save(questions);
		
	}
	
	
	
	//Assign answers to question******
	
		@PostMapping("/question/answer/{qid}/{aid}")
		public Questions assignQuestionToTopic(@PathVariable("qid") Long qid , @PathVariable("aid")Long aid) {
			
			Questions question=questionRepository.getById(qid);
			List<Answer>answers=question.getAnswer();
			
			Answer answer=answerRepository.getById(aid);
			answers.add(answer);
			
			question.setAnswer(answers);
			
			
			return questionRepository.save(question);
				
		}
		
		@GetMapping("/answer/{qid}")
		public List<AnswerDto> GetAnswerswithQuestionid(@PathVariable("qid") Long qid ) {
			
			//List<Answer>answer=answerRepository.findByQuestionsId(qid);
			List<Questions>question=questionRepository.findByQuestionsId(qid);
			
			//sorting question list according to date
			List<Questions>sorted=question.stream().sorted(Comparator.comparing(Questions::getDateofpost).reversed())
					.collect(Collectors.toList());
			//dto list to add list of answers in it
			List<AnswerDto>dtlist=new ArrayList<>();
			//dto object to add values in dto
			AnswerDto dto=new AnswerDto();
			for(Questions q:sorted) {
				dto.setQuestionId(q.getId());
				dto.setQuestionText(q.getQuestion_text());
				dto.setUsername(q.getUsername());
				dto.setDateOfPost(q.getDateofpost());
				dto.setNumberOfAnswer(q.getAnswer().size());
				dto.setAnswer(q.getAnswer());
				
				
			}
			dtlist.add(dto);
			
			return dtlist;
		}
		 
		@PutMapping("/question/put/{qid}")
		public Questions updateReview(@PathVariable("qid") Long qid,@RequestBody Questions questionNew,Principal principle)
		{
			Questions questionDB=questionRepository.getById(qid);
			
			String Username =principle.getName();
			
			if(questionNew.getQuestion_text()!=null)
				questionDB.setQuestion_text(questionNew.getQuestion_text());
			if(questionNew.getLikes()!= 0)
				questionDB.setLikes(questionNew.getLikes());
			
			String UserOwner=questionDB.getUsername();
			
			if(! Username.equalsIgnoreCase(UserOwner))
				throw new RuntimeException("user not allow to make any kind of edits in this question,as this  question owner is someone else" );
			
			return questionRepository.save(questionDB);
			
			
		}	
		
		@PutMapping("/answer/{aid}")
		public Answer updateReview(@PathVariable("aid") Long aid,@RequestBody Answer answernew,Principal principle)
		{
			Answer answerDB=answerRepository.getById(aid);
			
			String Username =principle.getName();
			
			if(answernew.getAnswer_text()!=null)
				answerDB.setAnswer_text(answernew.getAnswer_text());
			if(answernew.getLikes()!= 0)
				answerDB.setLikes(answernew.getLikes());
			
			String UserOwner=answerDB.getUsername();
			
			if(! Username.equalsIgnoreCase(UserOwner))
				throw new RuntimeException("user not allow to make any kind of edits in this Answer,as this Answer owner is someone else" );
			
			return answerRepository.save(answerDB);
			
			
		}	
}

