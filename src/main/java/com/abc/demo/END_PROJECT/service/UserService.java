package com.abc.demo.END_PROJECT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.END_PROJECT.entity.CommentsEntity;
import com.abc.demo.END_PROJECT.entity.LikeEntity;
import com.abc.demo.END_PROJECT.entity.UserEntity;
import com.abc.demo.END_PROJECT.entity.VideoEntity;
import com.abc.demo.END_PROJECT.repository.CommentRepository;
import com.abc.demo.END_PROJECT.repository.LikeRepository;
import com.abc.demo.END_PROJECT.repository.UserRepository;
import com.abc.demo.END_PROJECT.repository.VideoRepository;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	
	@Autowired
	LikeRepository lr;
	
	@Autowired
	CommentRepository cr;
	
	@Autowired
	VideoRepository vr;
	

	public void addUser(UserEntity user) {

		ur.save(user);
	}

	public void addLike(LikeEntity le) {
		
		VideoEntity ve = vr.findById(le.getVideo().getId()).get();
		ve.setLikescount(ve.getLikescount()+1);
		lr.save(le);
		vr.save(ve);
		
	}
	public boolean liked(LikeEntity le) {
		LikeEntity l1 = lr.findByUser_Video_Id(le.getUser().getId(),le.getVideo().getId());
		if(l1!=null) {
			return false;
		}
		else {
			return true;
		}
	}

	public void addComment(CommentsEntity ce) {
		
		cr.save(ce);
	}

	public void unlike(LikeEntity le) {
		VideoEntity ve = vr.findById(le.getVideo().getId()).get();
		ve.setLikescount(ve.getLikescount()-1);
		lr.delete(lr.findByUser_Video_Id(le.getUser().getId(),le.getVideo().getId()));
		vr.save(ve);
		
	}

	public void deleteComment(CommentsEntity ce) {
		CommentsEntity ct = cr.findByUser_Video_Id(ce.getUser().getId(),ce.getVideo().getId());
		cr.delete(ct);
	}
	

}
