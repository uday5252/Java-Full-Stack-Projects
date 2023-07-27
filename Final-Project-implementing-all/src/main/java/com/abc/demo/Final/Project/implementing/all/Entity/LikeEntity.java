package com.abc.demo.Final.Project.implementing.all.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="liketable")
public class LikeEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int LikeId;
	
	
	@ManyToOne()
	@JoinColumn(name="user")
	private UserEntity user;
	
	@ManyToOne()
	@JoinColumn(name="video")
	private VideoEntity video;

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
