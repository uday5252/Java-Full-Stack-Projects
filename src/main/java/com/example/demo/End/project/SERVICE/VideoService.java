package com.example.demo.End.project.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.End.project.ENTITY.GenreEntity;
import com.example.demo.End.project.ENTITY.UserEntity;
import com.example.demo.End.project.ENTITY.VideoEntity;
import com.example.demo.End.project.REPOSITORY.GenreRepositoryInterface;
import com.example.demo.End.project.REPOSITORY.UserRepositoryInterface;
import com.example.demo.End.project.REPOSITORY.VideoRepositoryInterface;

@Service
public class VideoService {

	@Autowired
	VideoRepositoryInterface vri;
	@Autowired
	GenreRepositoryInterface gri;
	@Autowired
	UserRepositoryInterface uri;
	public void uploadvideo(VideoEntity ve, int gid, int userid) {
		UserEntity ue = uri.findById(userid).get();
		GenreEntity ge = gri.findById(gid).get();
		ve.setGenre(ge);
		ve.setUser(ue);
		vri.save(ve);
	}
	public VideoEntity getVideo(int vid) {
		VideoEntity ve = vri.findById(vid).get();
		return ve;
		
	}
	public List<VideoEntity> getAllVideo() {
		List<VideoEntity> ve  = vri.findAll();
		return ve;
	}
	public List<VideoEntity> getgenreVideos(int genreid) {
		
		List<VideoEntity>ve = vri.findByGenreGenreid(genreid);
		return ve;
	}
	public void updateVideo(int id, VideoEntity ve) {
		VideoEntity ve1 = vri.findById(id).get();
		ve1.setDescription(ve.getDescription() !=null ? ve.getDescription() : ve1.getDescription());
		ve1.setTitle(ve.getTitle() !=null ? ve.getTitle() : ve1.getTitle());
		ve1.setUrl(ve.getUrl() != null ? ve.getUrl() : ve1.getUrl());
		
		vri.save(ve1);
		
	}
	public void deleteVideo(int id) {
	   vri.deleteById(id);
		
	}
//	public void sendDataToVideoUpload(String message) {
	

}
