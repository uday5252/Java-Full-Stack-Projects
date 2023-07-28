package com.project.VideoStreamingPlatformUsingSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.commentsEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.likesEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.ratingEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.usersEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.videosEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.CommentRepository;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.LikeRepository;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.RatingRepository;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.UserRepository;
import com.project.VideoStreamingPlatformUsingSpringBoot.repository.VideoRepository;

@Service
public class UserService {
	@Autowired
	UserRepository ur;
	@Autowired
	LikeRepository lr;
	@Autowired
	VideoRepository vr;
	@Autowired
	CommentRepository cr;
	@Autowired
	RatingRepository rr;
	
	public void createuser(usersEntity ue) {
		ur.save(ue);
	}

	public String likevideo(likesEntity le, int vid) {
		videosEntity ve = vr.findById(vid).get();
	    likesEntity le1 = lr.findByuser_video_id(le.getUser().getUserId(), vid);
		if(le1==null)
		{
			ve.setLikesCount(ve.getLikesCount()+1);
			le.setVideo(ve);
			lr.save(le);
			vr.save(ve);
			return "Video liked successfully";
		}
		return "User has already liked video";
	}
	
	public String commentvideo(commentsEntity ce,int vid) {
		videosEntity ve = vr.findById(vid).get();
		ve.setComments(ve.getComments()+1);
		ce.setVideo(ve);
		cr.save(ce);
		vr.save(ve);
		return "Commented successfully";
	}

	public Object unlikevideo(likesEntity le, int vid) {
		videosEntity ve = vr.findById(vid).get();
	    likesEntity le1 = lr.findByuser_video_id(le.getUser().getUserId(), vid);
		if(le1!=null)
		{
			ve.setLikesCount(ve.getLikesCount()-1);
			le.setVideo(ve);
			lr.deletebyuser_video_id(le.getUser().getUserId(),vid);
			vr.save(ve);
			return "Video Unliked successfully";
		}
		return "User has not liked video";
	}
	
	public commentsEntity getcommentbyid(int cid) {
		return cr.findById(cid).get();
	}
	public List<commentsEntity> getcomments(int vid) {
		return cr.findCommentsByvideoid(vid);
	}

	public void editcomment(commentsEntity ce, int cid) {
		commentsEntity ce1 = cr.findById(cid).get();
		ce1.setComment(ce.getComment());
		cr.save(ce1);
	}

	public void deletecomment(int cid) {
		commentsEntity ce = cr.findById(cid).get();
		videosEntity ve = vr.findById(ce.getVideo().getVideoId()).get();
		ve.setComments(ve.getComments()-1);
		cr.deleteById(cid);
		vr.save(ve);
	}

	public String ratevideo(ratingEntity re, int vid) {
		videosEntity ve = vr.findById(vid).get();
		ratingEntity re1 = rr.findbyuser_video_id(re.getUser().getUserId(),vid);
		if(re1==null)
		{
			re.setVideo(ve);
			rr.save(re);
			ve.setUsersrated(ve.getUsersrated()+1);
			ve.setRating((ve.getRating()+re.getRating())/ve.getUsersrated());
			vr.save(ve);
			return "Video Rated successfully";
		}
		return "User has already rated video";
	}
}
