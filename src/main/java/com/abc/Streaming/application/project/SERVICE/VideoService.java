package com.abc.Streaming.application.project.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.abc.Streaming.application.project.ENTITIES.GenreEntity;
import com.abc.Streaming.application.project.ENTITIES.VideoEntity;
import com.abc.Streaming.application.project.REPOSITORIES.VideoEntityInterfaceRepositary;

import reactor.core.publisher.Mono;

@Service
public class VideoService {
	@Autowired
	VideoEntityInterfaceRepositary v;
	
	public void addVideo(VideoEntity ve) {
		// TODO Auto-generated method stub
		v.save(ve);
		System.out.println(ve.getGenreId());
		System.out.println(ve.getUserId());
		
	}

	public VideoEntity readvideo(int vid) {
		// TODO Auto-generated method stub
		VideoEntity video = v.findById(vid).get();
		return video;
		
	}
	public List<VideoEntity> readallvideo() {
		List<VideoEntity>  l = v.findAll();
		return l;
	
	}

	public List<VideoEntity> readgenrevideo(int gid) {
		// TODO Auto-generated method stub
		
		List<VideoEntity> video = v.findByGenreIdId(gid);
		return video;
	}

	public void updatevideo(VideoEntity ve, int vid) {
		VideoEntity video =  v.findById(vid).get();
		video.setTitle(ve.getTitle()!=null?ve.getTitle():video.getTitle());
		video.setDescription(ve.getDescription()!=null?ve.getDescription():video.getDescription());
		video.setUrl(ve.getUrl()!=null?ve.getUrl():video.getUrl());
		video.setGenreId(ve.getGenreId()!=null?ve.getGenreId():video.getGenreId());
		video.setUserId(ve.getUserId()!=null?ve.getUserId():video.getUserId());
		v.save(video);
		
		
		
	}

	public void deletevideo(int vid) {
		// TODO Auto-generated method stub
		v.deleteById(vid);
		
	}
	
	@Autowired
	ResourceLoader loader;
	
    public Mono<Resource> streamvideo(String videoname)
    {
    	return Mono.fromSupplier(()->
		{
			return loader.getResource("classpath:videos/"+videoname+".mp4");
		});
    }


}
