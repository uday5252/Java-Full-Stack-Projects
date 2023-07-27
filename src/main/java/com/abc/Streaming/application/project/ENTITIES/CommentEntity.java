package com.abc.Streaming.application.project.ENTITIES;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comments")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	public CommentEntity(String description, int userId, int videoId) {
		super();
		this.description = description;
		this.userId = userId;
		this.videoId = videoId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne
	@JoinColumn(name = "userid" ,insertable = false, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity user;
	
	@Column(name = "userid")
	private int userId;
	
	
	@ManyToOne
	@JoinColumn(name = "videoid", insertable = false, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private VideoEntity video;
	
	@Column(name = "videoid")
	private int videoId;
	
	
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public VideoEntity getVideo() {
		return video;
	}
	public void setVideo(VideoEntity video) {
		this.video = video;
	}
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
