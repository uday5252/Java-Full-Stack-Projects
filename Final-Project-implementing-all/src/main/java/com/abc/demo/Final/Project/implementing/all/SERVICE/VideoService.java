package com.abc.demo.Final.Project.implementing.all.SERVICE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Final.Project.implementing.all.Entity.GenreEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;
import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.GenreRepository;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.UserRepository;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository vr;
	
	@Autowired 
	GenreRepository gr;
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	KafkaTemplate<Integer,String> kt;
	
	
	public void Videoadd(VideoEntity video,int gid,int uid) {
		GenreEntity g = gr.findById(gid).get();
		video.setGenre(g);
		UserEntity u = ur.findById(uid).get();
		video.setUploaded_by(u);
		vr.save(video);
		kt.send("VideoUploads","Video is Uploaded");
	}
	
	public VideoEntity Videoget(int vid) {
		VideoEntity v = vr.findById(vid).get();
		return v;
	}
	
	public List<VideoEntity> Videos(){
		List<VideoEntity> ve = vr.findAll();
		return ve;
	}
	
	public List<String> videoNames(){
		List<String> l = new ArrayList<>();
		List<VideoEntity> ve = vr.findAll();
		for(VideoEntity v :ve) {
			l.add(v.getTitle());
		}
		return l;
	}
	
	public List<VideoEntity> videoByGenre(int gid){
		
		GenreEntity genre = gr.findById(gid).get();
		
		
		List<VideoEntity> g = vr.findAllByGenre(genre);
		
		System.out.println(genre.getGid());
//		System.out.println(g);
		return g;
		
		}
	
	public void Videodelete(int vid) {
		VideoEntity v = vr.findById(vid).get();
		vr.delete(v);
	}
	
}
