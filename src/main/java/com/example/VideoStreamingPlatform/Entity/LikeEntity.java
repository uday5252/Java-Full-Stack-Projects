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
public class LikeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int like_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "video_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private VideoEntity video;

	public int getLike_id() {
		return like_id;
	}

//	public void setLike_id(int like_id) {
//		this.like_id = like_id;
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
