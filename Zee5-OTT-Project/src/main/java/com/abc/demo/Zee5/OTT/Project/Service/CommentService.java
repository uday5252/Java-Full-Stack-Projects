package com.abc.demo.Zee5.OTT.Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.Zee5.OTT.Project.Entity.Comment;
import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository cr;
	@Autowired
	KafkaAdmin Admin;
	@Autowired
	KafkaTemplate<String, String> kt;
	
	public void addComment(Comment commentnew,User user,Video video)
	{
		Comment comment=new Comment();
		
		comment.setUser(user);
		comment.setVideo(video);
		comment.setDescription(commentnew.getDescription());
		cr.save(comment);
		String User="user"+comment.getUser().getUser_id();
		
		kt.send(User,"USER "+comment.getUser().getUser_id()+" has Commented on the video "+comment.getVideo().getVideo_id());
		
	}
	
	public void deletecomment(int comment_id)
	{
		
		if(cr.findById(comment_id)!=null)
		{
			cr.deleteById(comment_id);

		}
	}
}
