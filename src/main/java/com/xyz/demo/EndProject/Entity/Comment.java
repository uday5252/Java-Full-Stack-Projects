package com.xyz.demo.EndProject.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Comment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int commentId;
    private String comment;
   	@ManyToOne
	@JoinColumn(name="userId")
   	@OnDelete(action = OnDeleteAction.CASCADE)
   private UserEntity user;
	@ManyToOne
	@JoinColumn(name="videoId")
	@OnDelete(action = OnDeleteAction.CASCADE)
   private VideoEntity video;
	@CreationTimestamp
   private String createdAt;
	
	
	public int getCommentId() {
		return commentId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(String comment,UserEntity user, VideoEntity video) {
		super();
		this.comment=comment;
		this.user = user;
		this.video = video;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment + ", user=" + user + ", video=" + video
				+ ", createdAt=" + createdAt + "]";
	}

	
}
