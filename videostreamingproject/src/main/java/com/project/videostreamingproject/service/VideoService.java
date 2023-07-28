package com.project.videostreamingproject.service;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.videostreamingproject.entity.Genre;
import com.project.videostreamingproject.entity.VideoTabel;
import com.project.videostreamingproject.helpfulclasses.VideoTabelDuplicate;
import com.project.videostreamingproject.repository.GenreRepository;
import com.project.videostreamingproject.repository.UserRepository;
import com.project.videostreamingproject.repository.VideoRepository;

import reactor.core.publisher.Mono; 

@Service
public class VideoService {
       
	@Autowired
	VideoRepository Vr;
	
	@Autowired
	ResourceLoader loader;
	
	@Autowired
	GenreRepository Gr;
	
	@Autowired
	UserRepository Ur;
	
	@Autowired
	GenreService Gs;
	
	
	@Autowired
	KafkaTemplate<String,String> kf;
	
	public Mono<Resource> getVideoLoading(String vName) {
		return Mono.fromSupplier(() -> {
			return loader.getResource("classpath:/videos/" + vName + ".mp4");
		});
	}
	
	public String videouri(String vName) throws IOException
	{
		return getVideoLoading(vName).block().getURL().toString();
	}
	
	
	
	public List<VideoTabel> findall()
	{
		return Vr.findAll();
	}
	public VideoTabel find(int id)
	{
		return Vr.findById(id).get();
	}
	
	public void delete(int id)
	{
		VideoTabel v=Vr.findById(id).get();
//		kf.send("VideoUrl",v.getUrl()+ " VIDEO WITH THIS URL IS DELETED BY"+ v.getUploadedBy().getUserName());
		Vr.delete(v);
		
	}
	
	public List<VideoTabel> findallofgenre(int id)
	{   
		Genre g=Gs.find(id);
		return Vr.findAllByGid(g);
	}
	public List<VideoTabel> findallofgenrebyname(String s)
	{   
		Genre g=Gr.findByGenreName(s);
		return Vr.findAllByGid(g);
	}
	
	

//	public void commentvideo(String comment,int vid)
//	{
//		VideoTabel vt=Vr.findById(vid).get();
//		List<String> l=vt.getComments();
//		if(l!=null)
//		{l.add(comment);
//		vt.setComments(l);}
//		else
//		{
//			List<String> lt=new ArrayList<>();
//			lt.add(comment);
//			vt.setComments(lt);
//		}
//	//	kf.send("USER-COMMENTS",vt.getTitle() + " --------" + comment);
//		Vr.save(vt);
//	}
	
	public void uploadvideos(VideoTabelDuplicate vt)
	{  
		VideoTabel vt1=new VideoTabel();
		vt1.setTitle(vt.getTitle());
		vt1.setDescription(vt.getDescription());
		vt1.setUrl(vt.getUrl());
		vt1.setGid(Gr.findById(vt.getGid()).get());
		vt1.setUploadedAt(LocalDateTime.now());
		vt1.setUploadedBy(Ur.findById(vt.getUploadedBy()).get());

		Vr.save(vt1);
		
	}
	
	public void update(int id,VideoTabelDuplicate vt)
	{
		VideoTabel vt1=Vr.findById(id).get();
		vt1.setDescription(vt.getDescription()==null?vt1.getDescription():vt.getDescription());
		vt1.setTitle(vt.getTitle()==null?vt1.getTitle():vt.getDescription());
		vt1.setUrl(vt.getUrl()==null?vt1.getUrl():vt.getUrl());
		Vr.save(vt1);
		
	}
	
	public VideoTabel getvideobyname(String name)
	{
		return Vr.findByTitle(name);
	}
	
}

