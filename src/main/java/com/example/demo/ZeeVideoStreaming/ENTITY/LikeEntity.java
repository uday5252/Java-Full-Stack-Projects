package com.example.demo.ZeeVideoStreaming.ENTITY;

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
@Table(name = "likes")
public class LikeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	long id;
	@ManyToOne
	@JoinColumn(name = "liked_by")
	@OnDelete(action = OnDeleteAction.CASCADE)
	UserEntity user;
	@ManyToOne
	@JoinColumn(name = "video_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	VideoEntity video;

	@CreationTimestamp
	String creationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public LikeEntity(UserEntity user, VideoEntity video) {
		super();
		this.user = user;
		this.video = video;
	}

	public LikeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
