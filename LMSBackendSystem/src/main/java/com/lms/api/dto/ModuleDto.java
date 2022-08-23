package com.lms.api.dto;

import java.util.List;

import com.lms.api.model.Video;

public class ModuleDto {

	private Long id;
	private String name;
	private int sequence;
	private List<Video>listvideo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int i) {
		this.sequence = i;
	}
	public List<Video> getListvideo() {
		return listvideo;
	}
	public void setListvideo(List<Video> listvideo) {
		this.listvideo = listvideo;
	}
	
}
