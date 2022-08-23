package com.lms.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.CourseStatsDto;
import com.lms.api.dto.ModuleDto;
import com.lms.api.model.Course;
import com.lms.api.model.Module;
import com.lms.api.model.Video;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.ModuleRepository;
import com.lms.api.repository.VideoRepository;

@RestController
public class VideoController {
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	
	@Autowired
	private CourseRepository courseRepository;
	/*
	 * module post API 
	 */
	@PostMapping("/module/{cid}")
	public Module postModule(@RequestBody Module module,@PathVariable("cid") Long cid) {
		
		Course course=courseRepository.getById(cid);
		module.setCourse(course);
		
	  	return moduleRepository.save(module);
		
	}
	
	
	/*
	 * Video post API
	 */
	
	@PostMapping("/video/{mid}")
	public Video postVideo(@RequestBody Video video,@PathVariable("mid") Long mid) {
		
	  	Module module=moduleRepository.getById(mid);
	
	  	video.setModule(module);
	  	return videoRepository.save(video);
		
	}
	/*
	 * Get all Modules by CourseId 
	 * Along with module, give video info as well
	 * 
	 * [ {
	 *    id:
	 *    name: ,
	 *    sequence: , 
	 *    videos:[
	 *       {
	 *          id:
	 *          title:
	 *          duration: 
	 *       },
	 *       {
	 *    	 
	 *       } 
	 *    ]
	 * 
	 * }, 
	 * {
	 *    id:
	 *    name: ,
	 *    sequence: , 
	 *    videos:[
	 *       {
	 *          id:
	 *          title:
	 *          duration: 
	 *       },
	 *       {
	 *    	 
	 *       } 
	 *    ]
	 *
	 * }
	 * ]
	 */
	
	  @GetMapping("/module/{cid}") 
	  public List<ModuleDto>getAllModule(@PathVariable("cid") Long cid){
		  
	  List<Module>list=moduleRepository.findByCourseId(cid);
	  
	  List<ModuleDto>dtolist=new ArrayList<>();
	  
	  list.stream().forEach(m->{
	  
	  ModuleDto dto=new ModuleDto();
	  
	  List<Video>listvideo=videoRepository.findByModuleId(m.getId());
	  
	  
	  dto.setId(m.getId()); 
	  
	  dto.setName(m.getName());
	  
	  dto.setSequence(m.getSequence()); 
	  
	  dto.setListvideo(listvideo);
	  
	  dtolist.add(dto);
	  
	  });
	  
	  return dtolist; 
	  
	  }
	  
	  @GetMapping("/module/alternative/{cid}") 
	  public List<ModuleDto> getAllModuleByCourse(@PathVariable("cid") Long cid){
	  
	  //fetch all modules based on courseId
	  
	  List<Module>list=moduleRepository.findByCourseId(cid);
	  List<ModuleDto>dtolist=new ArrayList<>();
	  
	  //fetch all videos for given courseId
	 
	 List<Video>listvideos=videoRepository.getByCourseId(cid);
	 
	  //iterate through each module to fetch list of videos for that module
	  
	  list.stream().forEach(m->{
	  
	 ModuleDto dto=new ModuleDto();
	  
	  //ftech videos for each moduleId
	  
	  List<Video>listvideo=listvideos.parallelStream()
	  .filter(v->v.getModule().getId().equals(m.getId()))
	  .collect(Collectors.toList()); 
	  
	  dto.setId(m.getId());
	  dto.setName(m.getName()); 
	  dto.setSequence(m.getSequence());
	  dto.setListvideo(listvideo); 
	  dtolist.add(dto); 
	  
	  });
	  
	  return dtolist; 
	  
	  }
	 
	/*
	 * {
	 * numModules
	 * numvideo
	 * content Duration :<display in hours and minutes>-136mins=2hrs,16min
	 * 
	 * }
	 */
	
	@GetMapping("/course/video/stats/{cid}")
	public CourseStatsDto CourseStatsByVideo(@PathVariable("cid") Long cid) {
		
		//fetch all modules based on courseId
		
		List<Module>listModules=moduleRepository.findByCourseId(cid);
		
		//ftech all videos for given coureId
		List<Video>listVideos=videoRepository.getByCourseId(cid);
		
		
		//calculate total duration of videos
		
		List<String>durationList=listVideos.stream()
									.map(v->v.getDuration())
									.collect(Collectors.toList());
		
		//System.out.println(durationList);//[8.30,7.30,10.30,5.30]
		
		int totalHours=0;
		int totalMinutes=0;
		int toatalSeconds=0;
		
		for(String d:durationList) {//8.30
			
			totalMinutes=totalMinutes+Integer.parseInt(d.split("\\.")[0]);//[8+7+10+5=30]
			toatalSeconds=toatalSeconds+Integer.parseInt(d.split("\\.")[1]);//[30+30+30+30=120]	
		}
		totalMinutes=totalMinutes+(toatalSeconds/60);
		toatalSeconds=toatalSeconds % 60;
		totalHours=(int)totalMinutes/60;
		
		CourseStatsDto dto=new CourseStatsDto();
		if(listModules!=null)
			dto.setNumModules(listModules.size());
		if(listVideos!=null)
			dto.setNumVideos(listVideos.size());
		
		dto.setContentDuration(totalHours+"hrs"+totalMinutes+"mins");
		
		return dto;
	}
	
}
