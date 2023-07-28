package com.example.VideoStreamingPlatform.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.Entity.GenreEntity;
import com.example.VideoStreamingPlatform.Entity.VideoEntity;
import com.example.VideoStreamingPlatform.Repository.GenreRepository;
import com.example.VideoStreamingPlatform.Repository.VideoRepository;

import reactor.core.publisher.Mono;

@Service
public class VideoService {
	
	@Autowired
	VideoRepository vr;
	@Autowired
	GenreRepository gr;
	
	@Autowired
	ResourceLoader loader;
	
	@Autowired
	KafkaAdmin admin;
	
	@Autowired
	KafkaTemplate<String, String> kt;
	
	public void addVideo(VideoEntity video, int id) {
		
		video.setGenre(gr.findById(id).get());
		vr.save(video);
		String Video = "create_video_topic";
		admin.createOrModifyTopics(TopicBuilder.name(Video).build());
		kt.send(Video, "A new video has been uploaded by "+video.getUploaded_by()+" with video id:"+video.getId());
	}
	
	public VideoEntity getDetailsByVideoId(int id) throws ResourceNotFoundException {
        Optional<VideoEntity> videoOptional = vr.findById(id);
        return videoOptional.orElseThrow(() -> new ResourceNotFoundException("Video not found for this id: " + id));
    }
	
	public List<VideoEntity> getAllVideos(){
		List<VideoEntity> list = new ArrayList<VideoEntity>();
		vr.findAll().forEach(video->list.add(video));
		return list;
	}
	
	public List<VideoEntity> getVideosByGenre(GenreEntity genre){
		List<VideoEntity> list = vr.getVideoByGenre(genre);
		return list;
	}
	
	
	public void updateVideo(VideoEntity video, int id) throws ResourceNotFoundException {
		
		VideoEntity existingVideo=vr.findById(id).orElseThrow(()-> new ResourceNotFoundException("No video found for this id"));
		existingVideo.setTitle(video.getTitle());
		existingVideo.setDescription(video.getDescription());
		existingVideo.setUrl(video.getUrl());
		existingVideo.setGenre(video.getGenre());
		existingVideo.setUploaded_by(video.getUploaded_by());
		vr.save(existingVideo);
	}
	
	public void deleteVideo(int video_id) {
		vr.deleteById(video_id);
	}
	
	public Mono<Resource> readVideo(String url){
		return Mono.fromSupplier(()->{
			return loader.getResource(url);
		});
	}
}
