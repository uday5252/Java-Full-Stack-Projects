package com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.VideoEntity;

import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.videoRepository;

@Service
public class videoService {
	
	
	@Autowired
	videoRepository  vr;

	public void addvideobyUser(VideoEntity ve) {
		
		vr.save(ve);
	}

	public VideoEntity getVideoById(int id) {
		
		VideoEntity video=vr.findById(id).orElse(null);
		
		return video;
		
	}

	public List<VideoEntity> getAllvideos() {
		
		List<VideoEntity> videos=vr.findAll();	
		
	return videos;	
	}

	public List<VideoEntity> getVideosbyGenreId(int genreId) {
		
		List<VideoEntity> Genrevideos=vr.findAllByGenreId(genreId);
		
		return Genrevideos;
		
	}

	public void deleteVideoById(int videoId) {
		vr.deleteById(videoId);
	}

	public void updateVideoDetails(int videoId, VideoEntity newVideoinfo) {
		
		VideoEntity video =vr.findById(videoId).get();
		
		
		video.setTitle(newVideoinfo.getTitle() != null ? newVideoinfo.getTitle():video.getTitle());
		
		video.setDescription(newVideoinfo.getDescription() != null ? newVideoinfo.getDescription():video.getDescription());
		
		video.setUrl(newVideoinfo.getUrl() != null ? newVideoinfo.getUrl():video.getUrl());
		
		
		vr.save(video);
		
		
	}

}
