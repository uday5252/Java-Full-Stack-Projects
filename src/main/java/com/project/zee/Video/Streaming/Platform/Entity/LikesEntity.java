package com.project.zee.Video.Streaming.Platform.Entity;

import java.time.Instant;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LikesEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn (name="liked_by")
	private UserEntity user;
	
	@ManyToOne
	@OnDelete (action = OnDeleteAction.CASCADE)
	@JoinColumn (name="video_id")
	private VideoEntity video;
	

	@Column(name="updated_at")
	@UpdateTimestamp
	private Instant updated_at;


	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public Instant getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}


	public LikesEntity(UserEntity user, VideoEntity video) {
		super();
		this.user = user;
		this.video = video;
	}


	public LikesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
