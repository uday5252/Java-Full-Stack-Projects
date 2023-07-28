package com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class commentEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	
	private String comment;
	

	@Column(insertable = false, updatable = false)
	private  int videoId;
	
	@Column(insertable = false, updatable = false)
	private int userId;
	
	
	@CreationTimestamp
	private String created_at;
	
	
	@ManyToOne
	@JoinColumn(name = "videoId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private VideoEntity videocomments;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private userEntity usercommented;

	
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	
	

	public userEntity getUsercommented() {
		return usercommented;
	}

	public void setUsercommented(userEntity usercommented) {
		this.usercommented = usercommented;
	}

	public commentEntity(String comment) {
		super();
		this.comment = comment;
	}

	public commentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public VideoEntity getVideocomments() {
		return videocomments;
	}

	public void setVideocomments(VideoEntity videocomments) {
		this.videocomments = videocomments;
	}

	
	
	
	
	
	
	
	

	
	

}
