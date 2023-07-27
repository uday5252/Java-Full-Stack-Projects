package com.abc.demo.Video.Management.Project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Video.Management.Project.entity.videoEntity;
import com.abc.demo.Video.Management.Project.repository.genreRepositoryInterface;
import com.abc.demo.Video.Management.Project.repository.userInterface;
import com.abc.demo.Video.Management.Project.repository.videoRepositoryInterface;

@Service
public class videoService {
	@Autowired
	videoRepositoryInterface vri;
	@Autowired
	userInterface ui;
	@Autowired
	genreRepositoryInterface gri;
	@Autowired
	KafkaTemplate<String,String> kt;
	public void addVideo(videoEntity ve,  int genreId,int userId) {
		ve.setGenre(gri.findById(genreId).get());
		ve.setUser(ui.findById(userId).get());
		vri.save(ve);
		String topic="UserTopic"+userId;
		kt.send(topic,"You uploaded a new video with id:"+ve.getVideoid());
	}
	public void deleteVideo(int Id) {
		String topic="UserTopic"+vri.findById(Id).get().getUser().getUserid();
		kt.send(topic,"You deleted the video with id:"+vri.findById(Id).get().getVideoid());
		vri.delete(vri.findById(Id).get());
	}
	public ArrayList<videoEntity> allVideo(int genreId,int userId)
	{
		return vri.findByUserUseridAndGenreGenreid(genreId, userId);
	}
	public ArrayList<videoEntity> allVideoByUser(int userId)
	{
		return vri.findByUserUserid(userId);
	}
	public ArrayList<videoEntity> allVideoByGenre(int genreId)
	{
		return vri.findByGenreGenreid(genreId);
	}
	public List<videoEntity> allVideos()
	{
		return vri.findAll();
	}
}
