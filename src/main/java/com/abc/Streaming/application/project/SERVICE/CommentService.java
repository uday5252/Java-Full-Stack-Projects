package com.abc.Streaming.application.project.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.Streaming.application.project.ENTITIES.CommentEntity;
import com.abc.Streaming.application.project.REPOSITORIES.CommentInterfaceRepositary;

@Service
public class CommentService {
	
	@Autowired
	CommentInterfaceRepositary c;

	public void addcomment(CommentEntity ce) {
		// TODO Auto-generated method stub
		c.save(ce);
		
	}

	public void uncomment(int uid)
	{
		c.delete(c.findByVideoId(uid));
	}
}
