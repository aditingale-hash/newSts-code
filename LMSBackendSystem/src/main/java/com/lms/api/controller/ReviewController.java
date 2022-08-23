
package com.lms.api.controller;


import java.security.Principal;
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

import com.lms.api.dto.ReviewstatsDto;
import com.lms.api.model.Course;
import com.lms.api.model.Review;
import com.lms.api.model.User;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.ReviewRepository;
import com.lms.api.repository.UserRepository;

@RestController
public class ReviewController {
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	  @Autowired 
	  private ReviewRepository reviewRepository;
	

	/*
	 * post api for review
	 */
	@PostMapping("/review/{cid}/{uid}")
	public Review postReview(@RequestBody Review review,@PathVariable("cid") Long cid,
			/*Principal principle*/@PathVariable("uid") Long uid ) {
		
		Course course=courseRepository.getById(cid);
		//User user=userRepository.findByUsername(principle.getName());
		User user=userRepository.getById(uid);
		 review.setCourse(course);
		 review.setUser(user);
		 
		 return reviewRepository.save(review);
		 
			
	}
	
	@GetMapping("/review")
	public List<Review> getAllProjects() {
		
		return  reviewRepository.findAll();
		
	}
	

	/*
	 * get all by course id
	 */
	
	@GetMapping("/review/{cid}")
	public List<ReviewstatsDto.ReviewDto> getByCourseId(@PathVariable("cid") Long cid) {
		
		List<Review>list=reviewRepository.findByCourseId(cid);
		
		List<ReviewstatsDto.ReviewDto>dtolist=new ArrayList<>();
		
		for(Review temp:list) {
			
			ReviewstatsDto.ReviewDto dto=new ReviewstatsDto.ReviewDto();
			
			dto.setId(temp.getId());
			dto.setContent(temp.getContent());
			dto.setRating((int)temp.getRating());
			dto.setUserId(temp.getUser().getId());
			dto.setName(temp.getUser().getName());
			dto.setEmail(temp.getUser().getEmail());
			dto.setCourseId(temp.getCourse().getId());
			dto.setCourseName(temp.getCourse().getName());
			dto.setLearningTrackId(temp.getCourse().getLearningTrack().getId());
			dto.setLearningTrack(temp.getCourse().getLearningTrack().getName());
			
			dtolist.add(dto);
		}
		/*
		 * ReviewDto dto=new ReviewDto(); dto=dto.convert(list);
		 */
		
		return dtolist;
		
	}
	
	@GetMapping("/review/sort-rating/{cid}")
	public List<Review> getReviewByCourseIdsortByRatingDESC(@PathVariable("cid") Long cid) {
		
		List<Review>list=reviewRepository.findByCourseId(cid);
		List<Review>sortedlist=list.stream()
				.sorted(Comparator.comparing(Review::getRating).reversed())
				.collect(Collectors.toList());
		return sortedlist;
		
	}
	
	@PutMapping("/review/{cid}")
	public Review updateReview(@PathVariable("cid") Long rid,@RequestBody Review reviewNew,Principal principle)
	{
		Review reviewDB=reviewRepository.getById(rid);
		
		String Username =principle.getName();
		
		if(reviewNew.getContent()!=null)
			reviewDB.setContent(reviewNew.getContent());
		if(reviewNew.getRating()!= 0)
			reviewDB.setRating(reviewNew.getRating());
		
		String UserOwner=reviewDB.getUser().getUsername();
		
		if(! Username.equalsIgnoreCase(UserOwner))
			throw new RuntimeException("user not allow to make any kind of edits in this comment,as this comment owner is someone else" );
		
		return reviewRepository.save(reviewDB);
		
		
	}	
	
	@GetMapping("/review/stats{cid}")
	public ReviewstatsDto getReviewStatsByCourseId(@PathVariable("cid") Long cid){
		
		List<Review>list=reviewRepository.findByCourseId(cid);
		ReviewstatsDto dto=new ReviewstatsDto();
		
		dto.setTotal(list.size());
		dto.setFivestar(list.stream().filter(r->r.getRating()==5).collect(Collectors.toList()).size());
		dto.setFourstar(list.stream().filter(r->r.getRating()==4).collect(Collectors.toList()).size());
		dto.setThreestar(list.stream().filter(r->r.getRating()==3).collect(Collectors.toList()).size());
		dto.setTwostar(list.stream().filter(r->r.getRating()==2).collect(Collectors.toList()).size());
		dto.setOnestar(list.stream().filter(r->r.getRating()==1).collect(Collectors.toList()).size());
		dto.setNoRatings(list.stream().filter(r->r.getRating()==0).collect(Collectors.toList()).size());
   return dto;
	
}
	
	
	
	
	
}
