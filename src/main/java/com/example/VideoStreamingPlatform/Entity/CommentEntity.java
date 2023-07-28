package com.example.VideoStreamingPlatform.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 int comment_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "video_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	VideoEntity video;
	
	String comment;

	public CommentEntity(UserEntity user, VideoEntity video, String comment) {
		super();
		this.user = user;
		this.video = video;
		this.comment=comment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getComment_id() {
		return comment_id;
	}

//	public void setComment_id(int comment_id) {
//		this.comment_id = comment_id;
//	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public VideoEntity getVideo() {
		return video;
	}

	public void setVideo(VideoEntity video) {
		this.video = video;
	}

	
}
