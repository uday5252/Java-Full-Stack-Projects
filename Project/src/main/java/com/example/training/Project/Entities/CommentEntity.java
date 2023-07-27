package com.example.training.Project.Entities;

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
@Table(name = "comments")
public class CommentEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity userId;
	
	
	@ManyToOne
	@JoinColumn(name = "videoId", insertable = false, updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private VideoEntity videoId;
	
	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CommentEntity(UserEntity user, VideoEntity video) {
		super();
		this.userId = user;
		this.videoId = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity getUserid() {
		return userId;
	}

	public void setUserId(UserEntity user) {
		this.userId = user;
	}


	public VideoEntity getVideoId() {
		return videoId;
	}

	public void setVideoId(VideoEntity video) {
		this.videoId = video;
	}
    
}
