package com.project.zee.Video.Streaming.Platform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.project.zee.Video.Streaming.Platform.Entity.VideoEntity;
import com.project.zee.Video.Streaming.Platform.Repository.GenreInterface;
import com.project.zee.Video.Streaming.Platform.Repository.UserInterface;
import com.project.zee.Video.Streaming.Platform.Repository.VideoInterface;

@Service
public class StreamingService {
	
	@Autowired
	VideoInterface vi;
	
	@Autowired 
	GenreInterface gi;
	
	@Autowired 
	UserInterface ui;
	
	@Autowired
	ResourceLoader loader;

	
	public String PlaySpecificVideo(int videoId) {
		
		VideoEntity video = vi.findById(videoId).get();
		String url = video.getUrl();
		return url;
	}
	
//	public List<Resource> PlayAllVideos()
//	{
//		List<VideoEntity> videos = vi.findAll();
//		List<Resource> AllVideos = new ArrayList<> ();
//		
//		for(VideoEntity v:videos)
//		{
//			Resource videoResource = loader.getResource("classpath:videos/"+v.getGenre().getName()+".mp4");
//			AllVideos.add(videoResource);
//		}
//		return AllVideos;
//		
//	}
	
	public List<VideoEntity> PlayAllVideos()
	{
		List<VideoEntity> videos = vi.findAll();
		return videos;
	}
	
	public List<VideoEntity> GenreVideos(int genreid)
	{
		List<VideoEntity> genrevideos = vi.findByGenreId(genreid);
		return genrevideos;
		
	}
	
	
	

}
