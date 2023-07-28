package com.example.endtoendproject.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.endtoendproject.ENTITY.Likes;
import com.example.endtoendproject.REPOSITORY.LikeManagementInterface;
import com.example.endtoendproject.REPOSITORY.VideoManagementInterface;

@Service
public class LikeService {
	@Autowired
	LikeManagementInterface lmi;
	@Autowired
	VideoManagementInterface vmi;
	//create a like
	public void createLike(Likes like, int videoid) {
		like.setVideo(vmi.findById(videoid).get());
		lmi.save(like);
	}
	
}
