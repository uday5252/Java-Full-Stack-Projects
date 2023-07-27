package com.example.demo.ZeeVideoStreaming.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.ZeeVideoStreaming.ENTITY.CommentEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.VideoEntity;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.CommentRepositoryInterface;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.UserRepositoryInterface;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.VideoRepositoryInterface;

@Service
public class CommentService {

	@Autowired
	CommentRepositoryInterface cri;

	@Autowired
	VideoRepositoryInterface vri;

	@Autowired
	UserRepositoryInterface uri;

	@Autowired
	KafkaTemplate<Integer, String> kt;

	public void createCommentInDB(long vid, CommentEntity c) {

		VideoEntity v = vri.findById(vid).get();
		c.setVideo(v);
		UserEntity user = uri.findById(c.getUser().getId()).get();
		c.setUser(user);

		String userName = c.getUser().getUserName();

		String videoName = c.getVideo().getTitle();

		String data = String.format("User %1$s commented on %2$s. Comment is -> \"%3$s.\" ", userName, videoName,
				c.getComment());
		kt.send("user_comments", data);
		cri.save(c);
	}

	public CommentEntity updateCommentInDB(long id, CommentEntity c) {
		CommentEntity commentFromDB = cri.findById(id).get();
		if (c.getComment() != null) {

			commentFromDB.setComment(c.getComment());
			
			String userName = commentFromDB.getUser().getUserName();

			String videoName = commentFromDB.getVideo().getTitle();

			String data = String.format("User %1$s updated commented on %2$s. Comment is -> \"%3$s.\" ", userName,
					videoName, commentFromDB.getComment());
			kt.send("user_comments", data);
		}
		cri.save(commentFromDB);
		return commentFromDB;
	}

	public void deleteCommentInDB(long id) {
		CommentEntity commentFromDB = cri.findById(id).get();
		if (commentFromDB != null) {

			String userName = commentFromDB.getUser().getUserName();

			String videoName = commentFromDB.getVideo().getTitle();

			String data = String.format("User %1$s deleted comment on %2$s. Comment is -> \"%3$s.\" ", userName,
					videoName, commentFromDB.getComment());
			kt.send("user_comments", data);
			cri.delete(commentFromDB);
		}

	}
	
	public List<CommentEntity> getCommentsByVideoID(long vid) {
		return cri.findByVideoId(vid);
	}
}
