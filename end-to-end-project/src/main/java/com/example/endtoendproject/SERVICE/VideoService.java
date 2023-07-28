package com.example.endtoendproject.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.endtoendproject.ENTITY.Genre;
import com.example.endtoendproject.ENTITY.Video;
import com.example.endtoendproject.REPOSITORY.GenreMangementInterface;
import com.example.endtoendproject.REPOSITORY.VideoManagementInterface;

import reactor.core.publisher.Mono;

@Service
public class VideoService {
	@Autowired
	GenreMangementInterface gmi;
	@Autowired
	VideoManagementInterface vmi;
//	@Autowired
//	ResourceLoader loader;
	
	//for reading a video
//	public Mono<Resource> readVideo(String videoName){
//		return Mono.fromSupplier(()->{
//			return loader.getResource("classpath:videos/"+videoName+".mp4");
//		});
//	}
	//for uploading new
	public void uploadVideo(Video video,int genreId) {
		video.setGenre(gmi.findById(genreId).get());
		vmi.save(video);
	}
	
	//video by Id
	public Video getVideoById(int vid) {
		Video video = vmi.findById(vid).get();
		return video;
	}
	
	//all videos of specific genreId
	public List<Video> getVideoByGenre(int gid){
		Genre g = gmi.findById(gid).get();
		return vmi.findBygenre(g);
	}
	
	//all videos
	public List<Video> getAllVideos(){
		List<Video> videoList = vmi.findAll();
		return videoList;
	}
	
	//delete a video
	public void deleteVideo(int vid) {
		Video video = vmi.findById(vid).get();
		vmi.delete(video);
	}
	
}
