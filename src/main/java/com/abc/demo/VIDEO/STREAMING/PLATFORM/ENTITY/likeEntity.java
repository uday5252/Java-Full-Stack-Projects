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
public class likeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;
	
	@Column(insertable = false, updatable = false)
	private  int videoId;
	
	@Column(insertable = false, updatable = false)
	private int userId;
	
	
	@CreationTimestamp
	private String created_at;
	
	
	@ManyToOne
	@JoinColumn(name = "videoId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private VideoEntity videolikes;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private userEntity userLiked;


	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public VideoEntity getVideolikes() {
		return videolikes;
	}

	public void setVideolikes(VideoEntity videolikes) {
		this.videolikes = videolikes;
	}

	public userEntity getUserLiked() {
		return userLiked;
	}

	public void setUserLiked(userEntity userLiked) {
		this.userLiked = userLiked;
	}

	public likeEntity() {
		super();
	}
	
	
	

}