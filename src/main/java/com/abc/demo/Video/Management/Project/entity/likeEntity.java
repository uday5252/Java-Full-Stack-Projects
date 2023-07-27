package com.abc.demo.Video.Management.Project.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class likeEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int likeid;
	@UpdateTimestamp
	private String updated_At;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private userEntity user;
	
	@ManyToOne
	@JoinColumn(name = "video_Id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private videoEntity video;
	
	public likeEntity() {
		super();
	}

	public int getLikeid() {
		return likeid;
	}

	public String getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(String updated_At) {
		this.updated_At = updated_At;
	}

	public userEntity getUser() {
		return user;
	}

	public void setUser(userEntity user) {
		this.user = user;
	}

	public videoEntity getVideo() {
		return video;
	}

	public void setVideo(videoEntity video) {
		this.video = video;
	}
}
