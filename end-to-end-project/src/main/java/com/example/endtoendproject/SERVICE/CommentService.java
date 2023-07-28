package com.example.endtoendproject.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.endtoendproject.ENTITY.Comments;
import com.example.endtoendproject.REPOSITORY.CommentManagementInterface;
import com.example.endtoendproject.REPOSITORY.VideoManagementInterface;

@Service
public class CommentService {
	@Autowired
	CommentManagementInterface cmi;
	@Autowired
	VideoManagementInterface vmi;
	
	//create a comment
	public void createComent(Comments comment, int videoid) {
	comment.setVideo(vmi.findById(videoid).get());
	cmi.save(comment);
	}
}