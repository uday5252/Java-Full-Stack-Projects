package com.example.demo.End.project.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.End.project.ENTITY.CommentEntity;
import com.example.demo.End.project.ENTITY.LikeEntity;
import com.example.demo.End.project.ENTITY.UserEntity;
import com.example.demo.End.project.ENTITY.VideoEntity;
import com.example.demo.End.project.REPOSITORY.CommentRepositoryInterface;
import com.example.demo.End.project.REPOSITORY.LikeRepositoryInterface;
import com.example.demo.End.project.REPOSITORY.UserRepositoryInterface;
import com.example.demo.End.project.REPOSITORY.VideoRepositoryInterface;
import com.example.demo.End.project.SERVICE.UserService;

@Controller
public class UserController {
    
	@Autowired 
	UserService us;

	@Autowired 
	LikeRepositoryInterface lri;
	@Autowired
	VideoRepositoryInterface vri;
	@Autowired
	CommentRepositoryInterface cri;
	@Autowired
	UserRepositoryInterface uri;
	
	@PostMapping("/api/user")
	public ResponseEntity<String> createUser(@RequestBody UserEntity ue)
	{
		us.createUser(ue);
		return new ResponseEntity<>("User created Successfully", HttpStatus.ACCEPTED);	
	}
	
	@PostMapping("/api/like/{userid}/{videoid}")
	public ResponseEntity<String> likeVideo(@PathVariable("userid") int userid, @PathVariable("videoid") int videoid)
	{
		LikeEntity le1 = lri.findByUeUseridAndVeVideoid(userid, videoid);
		if(le1 != null)
		{
			return new ResponseEntity<>("User already liked this video", HttpStatus.ACCEPTED);
		}
		else {
			VideoEntity ve = vri.findById(videoid).get();
			ve.setLikesCount(ve.getLikesCount()+1);
			LikeEntity le = new LikeEntity();
			UserEntity ue = uri.findById(userid).get();
			VideoEntity ve1 = vri.findById(videoid).get();
			le.setUe(ue);
			le.setVe(ve1);
			lri.save(le);
			vri.save(ve1);
			
			String message = "user with user ID " + userid + "liked video with video id "+videoid;
			us.sendDataToUserLikes(message);
			return new ResponseEntity<>("'Liked Successfully", HttpStatus.OK);
		}
	}
	@PostMapping("/api/dislike/{userid}/{videoid}")
	public ResponseEntity<String> dislikeVideo(@PathVariable("userid") int userid, @PathVariable("videoid") int videoid)
	{
		LikeEntity le1 = lri.findByUeUseridAndVeVideoid(userid, videoid);
		if(le1 == null)
		{
			return new ResponseEntity<>("User not liked this video before", HttpStatus.ACCEPTED);
		}
		else {
			VideoEntity ve = vri.findById(videoid).get();
			ve.setLikesCount(ve.getLikesCount()-1);
		    
			LikeEntity li=lri.findByUeUseridAndVeVideoid(userid,videoid);
			lri.deleteById(li.getLikeid());
			
			String message = "user with user ID " + userid + "disliked video with video id "+videoid;
			us.sendDataToUserLikes(message);
			return new ResponseEntity<>("'disliked Successfully", HttpStatus.OK);
		}
	}
	@PostMapping("/api/comment/{userid}/{videoid}")
	public ResponseEntity<String> commentVideo(@RequestBody CommentEntity le ,@PathVariable("userid") int userid, @PathVariable("videoid") int videoid )
	{
		CommentEntity le1 = cri.findByUeUseridAndVeVideoid(userid, videoid);
		
			VideoEntity ve = vri.findById(videoid).get();
			//ve.setLikesCount(ve.getLikesCount()+1);
			CommentEntity le11 = new CommentEntity();
			UserEntity ue = uri.findById(userid).get();
			VideoEntity ve1 = vri.findById(videoid).get();
			le11.setUe(ue);
			le11.setVe(ve1);
			le11.setComment(le.getComment());
			cri.save(le11);
			
			String message = "user with user ID" + userid + "comment video with video id"+videoid;
			us.sendDataToUserComments(message);
			return new ResponseEntity<>("'commented Successfully", HttpStatus.OK);
	}
	@DeleteMapping("/api/comment/{userid}/{videoid}")
	public ResponseEntity<String> deletecomment(@PathVariable("userid") int userid, @PathVariable("videoid") int videoid)
	{
		CommentEntity le1 = cri.findByUeUseridAndVeVideoid(userid, videoid);
		
			VideoEntity ve = vri.findById(videoid).get();
			//ve.setLikesCount(ve.getLikesCount()+1);
			
			cri.deleteById(le1.getCommentid());
			
			
			String message = "user with user ID" + userid + "deleted comment for video with video id"+videoid;
			us.sendDataToUserComments(message);
			return new ResponseEntity<>("'comment deleted Successfully", HttpStatus.OK);
	}
	
	
	
	
	
}
