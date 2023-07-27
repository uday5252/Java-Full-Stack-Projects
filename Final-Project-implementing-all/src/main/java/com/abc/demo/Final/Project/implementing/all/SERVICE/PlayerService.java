package com.abc.demo.Final.Project.implementing.all.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.Final.Project.implementing.all.Entity.VideoEntity;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.VideoRepository;

@Service
public class PlayerService {

	@Autowired
	VideoRepository vr;
	
	public String getVideo(int vid) {
		VideoEntity v = vr.findById(vid).get();
		String url = v.getUrl();
		System.out.println(url);
		
		return url;
	}
	
}
