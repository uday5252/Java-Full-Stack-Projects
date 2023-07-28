package com.project.VideoStreamingPlatformUsingSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.videosEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.UserRepository;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.VideoRepository;

@Service
public class VideoService {
	@Autowired
	VideoRepository vr;
	@Autowired
	UserRepository ur;
	
	public void addvideo(videosEntity ve) {
		vr.save(ve);
	}

	public List<videosEntity> listvideos() {
		return vr.findAll();
	}

	public List<videosEntity> listvideosByGenre(int gid) {
		
		return vr.findBygenreid(gid);
	}

	public void updatevideoDetails(int vid, videosEntity ve) {
		videosEntity ve1 = vr.findById(vid).get();
		ve1.setDescription((ve.getDescription()!=null)?ve.getDescription():ve1.getDescription());
		ve1.setGenre((ve.getGenre()!=null)?ve.getGenre():ve1.getGenre());
		ve1.setUser((ve.getUser()!=null)?ve.getUser():ve1.getUser());
		ve1.setTitle((ve.getTitle()!=null)?ve.getTitle():ve1.getTitle());
		ve1.setUrl((ve.getUrl()!=null)?ve.getUrl():ve1.getUrl());
		vr.save(ve1);
	}

	public void deletevideo(int vid) {
		vr.deleteById(vid);
	}

	public videosEntity getvideo(int vid) {
		return vr.findById(vid).get();
	}
	
}
