package com.project.ZeeWebApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.project.ZeeWebApplication.Entity.GenreEntity;
import com.project.ZeeWebApplication.Entity.UserEntity;
import com.project.ZeeWebApplication.Entity.VideoEntity;
import com.project.ZeeWebApplication.RapperClass.RapperClass;
import com.project.ZeeWebApplication.Repository.GenreRepositoryInterface;
import com.project.ZeeWebApplication.Repository.UserRepositoryInterface;
import com.project.ZeeWebApplication.Repository.VideoRepositoryInterface;



@Service
public class VideoService {

	@Autowired
	VideoRepositoryInterface vri;
	
	@Autowired
	UserRepositoryInterface uri;
	
	@Autowired
	GenreRepositoryInterface gri;
	
	@Autowired
	KafkaTemplate<Integer, String> kt;
	

	public void UploadTheVideo(RapperClass rc) {
		
        VideoEntity ve = new VideoEntity();
		
		ve.setDescription(rc.getDescription());
		ve.setTitle(rc.getTitle());
		ve.setUrl(rc.getUrl());
		
		int userid = rc.getUserid();
		
		UserEntity userentity = uri.findById(userid).get();
		ve.setUser(userentity);
		 
		String genrename = rc.getGenrename();
		
		GenreEntity genreentity = gri.findByGname(genrename);
		ve.setGenre(genreentity);
		
		vri.save(ve);
	}

	public VideoEntity getTheVideoInformation(int vid) {
		
		VideoEntity ve = vri.findById(vid).get();
		
		return ve;
		
	}

	public List<VideoEntity> getAllVideoInformation() {
		
		return vri.findAll();
	}

	public void UpdateInformation(RapperClass rp, int vid) {
		
		VideoEntity olddata = vri.findById(vid).get();
		
		olddata.setTitle(rp.getTitle() != null ? rp.getTitle() : olddata.getTitle());
		
		olddata.setDescription(rp.getDescription() != null ? rp.getDescription() : olddata.getDescription());
		
		olddata.setUrl(rp.getUrl() != null ? rp.getUrl() : olddata.getUrl());
		
		olddata.setGenre(rp.getGenrename() != null ? gri.findByGname(rp.getGenrename()) : olddata.getGenre());
		
		olddata.setUser(rp.getUserid() != 0 ? uri.findById(rp.getUserid()).get() : olddata.getUser());
		
		vri.save(olddata);
	}

	public void DeletetheVideo(int vid) {
		
		vri.deleteById(vid);
		
	}

	public List<VideoEntity> getGenreVideos(int gid) {
		
		List<VideoEntity> allv = vri.findByGenreId(gid);
		
		return allv;
		
	}

	public String getTheVideoPath(int vid) {
		
		VideoEntity vs  = vri.findById(vid).get();
		
		String path = vs.getUrl();
		
		return path;
	}

	public List<VideoEntity> getAllGenreVideoPaths(int gid) {
		
		List<VideoEntity> list = vri.findByGenreId(gid);
		
		return list;
	}

	public List<VideoEntity> getallvideosofDB() {
		
		List<VideoEntity> allv = vri.findAll();
		
		return allv;
	}

	public void sendDataToVideoTopic(int uid, String gname,String link) {
	   
		String message = Integer.toString(uid) + " id user uploaded the Video of Genre:- " + gname + " with link:-  " + link;
		
		kt.send("video_uploads",message);
		
	}
	
}
