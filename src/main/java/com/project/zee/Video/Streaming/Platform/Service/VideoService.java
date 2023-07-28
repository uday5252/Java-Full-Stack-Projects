package com.project.zee.Video.Streaming.Platform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;
import com.project.zee.Video.Streaming.Platform.Repository.GenreInterface;
import com.project.zee.Video.Streaming.Platform.Repository.UserInterface;
import com.project.zee.Video.Streaming.Platform.Repository.VideoInterface;
import com.project.zee.Video.Streaming.Platform.WrapperClass.WrapperClass;



@Service
public class VideoService {
	
	@Autowired
	VideoInterface vi;
	
	@Autowired 
	GenreInterface gi;
	
	@Autowired 
	UserInterface ui;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	

	public void uploadvideo(WrapperClass request) {
		
		System.out.println(request);
		VideoEntity ve = new VideoEntity();
		
		ve.setDescription(request.getDescription());
		ve.setGenre(gi.findById(request.getGenreid()).get());
		ve.setTitle(request.getTitle());
		ve.setUrl(request.getUrl());
		ve.setUser(ui.findById(request.getUserid()).get());
		
		vi.save(ve);
		
	}
	
	public VideoEntity getVideoDetails(int videoid)
	{
		return vi.findById(videoid).get();
	}
	
	public List<VideoEntity> getVideos()
	{
		List<VideoEntity> videos=vi.findAll();
		return videos;
	}
	
	public List<VideoEntity> getGenreVideos(int gid)
	{
		List<VideoEntity> videos = vi.findByGenreId(gid);
		return videos;
	}
	
	public void updateVideoDetails(WrapperClass updateddetails,int vid)
	{
		VideoEntity video =vi.findById(vid).get();
		
		video.setDescription( updateddetails.getDescription() !=null ? updateddetails.getDescription() : video.getDescription());
		video.setTitle( updateddetails.getTitle() !=null ? updateddetails.getTitle() : video.getTitle());
		video.setUrl( updateddetails.getUrl() !=null ? updateddetails.getUrl() : video.getUrl());
		
		//update genreid
		
		video.setGenre(gi.findById(updateddetails.getGenreid() !=0 ? updateddetails.getGenreid() : video.getGenre().getId()).get());   
		
		//update userid
		
		video.setUser(ui.findById(updateddetails.getUserid() !=0 ? updateddetails.getUserid() : video.getUser().getId()).get());
		vi.save(video);
	}
	
	public void deleteVideo(int vid)
	{
		vi.deleteById(vid);
	}
	

	public void sendUploadedVideosDataToVideosTopic(WrapperClass request)
	{	
		
		
		String VideosUpdate = "User " + request.getUserid() + " uploaded a video ";
		kt.send("VideosDataTopic",VideosUpdate);
		
	}
	
	public void sendEditedVideosDataToVideosTopic(int vid)
	{	
		
		String VideosUpdate = "Admin updated video "+ vid + "details";
		kt.send("VideosDataTopic",VideosUpdate);
		
	}

	public void senddeletedVideosDataToVideosTopic(int vid)
	{	
		
		String VideosUpdate = "Admin deleted video "+ vid ;
		kt.send("VideosDataTopic",VideosUpdate);
		
	}

	
}
