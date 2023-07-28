package com.abc.demo.END_PROJECT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.END_PROJECT.entity.GenreEntity;
import com.abc.demo.END_PROJECT.entity.UserEntity;
import com.abc.demo.END_PROJECT.entity.VideoEntity;
import com.abc.demo.END_PROJECT.repository.GenreRepository;
import com.abc.demo.END_PROJECT.repository.UserRepository;
import com.abc.demo.END_PROJECT.repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	VideoRepository vr;
	@Autowired
	UserRepository ur;
	@Autowired
	GenreRepository gr;

	public void uploadVideo(VideoEntity video, int uid, int gid) {

		UserEntity ue = ur.findById(uid).get();
		GenreEntity ge = gr.findById(gid).get();
		video.setGenre_id(ge);
		video.setUploaded_by(ue);
		vr.save(video);
	}

	public VideoEntity readVideo(int vid) {
		VideoEntity ve = vr.findById(vid).get();
		return ve;
	}

	public List<VideoEntity> readAllVideos() {
		List<VideoEntity> ve = vr.findAll();
		return ve;
	}

	public List<VideoEntity> readVideoByGenre(int gid) {
		List<VideoEntity> ve = vr.findByGenreid(gid);
		return ve;
	}

	public void updateVideo(VideoEntity video, int vid) {
		VideoEntity ve = vr.findById(vid).get();
		ve.setTitle(video.getTitle()!=null ? video.getTitle():ve.getTitle());
		ve.setDescription(video.getDescription()!=null ? video.getDescription():ve.getDescription());
		ve.setUrl(video.getUrl()!=null ? video.getUrl():ve.getUrl());
		ve.setGenre_id(video.getGenre_id()!=null ? video.getGenre_id():ve.getGenre_id());
		ve.setUploaded_by(video.getUploaded_by()!=null ? video.getUploaded_by():ve.getUploaded_by());
		vr.save(ve);		
	}

	public VideoEntity deleteVideo(int vid) {
		VideoEntity ve = vr.findById(vid).get();
		
		vr.delete(ve);	
		return ve;
	}

	
}
