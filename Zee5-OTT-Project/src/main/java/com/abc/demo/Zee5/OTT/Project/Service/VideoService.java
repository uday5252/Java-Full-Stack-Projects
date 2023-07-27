package com.abc.demo.Zee5.OTT.Project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Zee5.OTT.Project.Entity.Genre;
import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Repository.GenreRepository;
import com.abc.demo.Zee5.OTT.Project.Repository.UserRepository;
import com.abc.demo.Zee5.OTT.Project.Repository.VideoRepository;

@Service
public class VideoService {
	
	@Autowired
	GenreRepository gr;

	@Autowired
	VideoRepository vr;

	@Autowired
	UserRepository ur;
	@Autowired
	KafkaAdmin Admin;
	@Autowired
	KafkaTemplate<String, String> kt;

	public void addVideo(Video videoData, int genre_id, int user_id){
	Genre genre = gr.findById(genre_id).get();
	videoData.setGenre(genre);
	User user = ur.findById(user_id).get();
	videoData.setUser(user);
	
	vr.save(videoData);
	
	String User="user"+videoData.getUser().getUser_id();
	
	kt.send(User,"USER "+videoData.getUser().getUser_id()+" has added the video "+videoData.getVideo_id());
	}
	public List<Video> listAllVideos()
	{
		return vr.findAll();
	}
	public Video fetchVideoById(int id)
	{
		return vr.findById(id).get();
	}
	public void updateVideo(Video video,int video_id)
	{
		
	
		Video video_old=vr.findById(video_id).get();
	
		video_old.setUser(video.getUser()!=null?video.getUser():video_old.getUser());
		video_old.setGenre(video.getGenre()!=null?video.getGenre():video_old.getGenre());
		video_old.setTitle(video.getTitle()!=null?video.getTitle():video_old.getTitle());
		video_old.setDescription(video.getDescription()!=null?video.getDescription():video_old.getDescription());
		video_old.setUrl(video.getUrl()!=null?video.getUrl():video_old.getUrl());
		vr.save(video_old);
		
		
	}
	public void deleteVideo(int video_id)
	{
		vr.deleteById(video_id);
	}
	public List<Video> getVideoByGenre(int genre_id)
	{
		Genre genre=gr.findById(genre_id).get();
		return vr.findByGenre(genre);
	}
	

}
