package com.project.ZeeWebApplication.Entity;

import java.time.Instant;


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
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentid;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "commented_by")
	private UserEntity user;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "commented_on")
	private VideoEntity video;
	
	private String commdescription;
	
	@CreationTimestamp
	@Column(name = "liked_at")
	private Instant commentedat;

	public CommentEntity(String commdescription) {
		super();
		this.commdescription = commdescription;
	}

	public CommentEntity() {
		super();
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

	public String getCommdescription() {
		return commdescription;
	}

	public void setCommdescription(String commdescription) {
		this.commdescription = commdescription;
	}
	
}
