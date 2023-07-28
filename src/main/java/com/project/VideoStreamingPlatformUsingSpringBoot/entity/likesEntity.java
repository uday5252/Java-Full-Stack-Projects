package com.project.VideoStreamingPlatformUsingSpringBoot.entity;


import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="likes")
public class likesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private usersEntity user;
	@ManyToOne
	@JoinColumn(name="videoId",referencedColumnName = "videoId")
	private videosEntity video;
	@CreationTimestamp
	private Timestamp likedAt;
	
	public likesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public usersEntity getUser() {
		return user;
	}

	public void setUser(usersEntity user) {
		this.user = user;
	}

	public videosEntity getVideo() {
		return video;
	}

	public void setVideo(videosEntity video) {
		this.video = video;
	}

	public Timestamp getLikedAt() {
		return likedAt;
	}

	public void setLikedAt(Timestamp likedAt) {
		this.likedAt = likedAt;
	}

	@Override
	public String toString() {
		return "likesEntity [likeId=" + likeId + ", user=" + user + ", video=" + video + ", likedAt=" + likedAt + "]";
	}
	
	
}
