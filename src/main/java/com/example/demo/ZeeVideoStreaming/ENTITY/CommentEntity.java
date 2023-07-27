package com.example.demo.ZeeVideoStreaming.ENTITY;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	String comment;
	@CreationTimestamp
	String createdAt;
	@UpdateTimestamp
	String updatedAt;

	@ManyToOne
	@JoinColumn(name = "commented_by")
	@OnDelete(action = OnDeleteAction.CASCADE)
	UserEntity user;

	@ManyToOne
	@JoinColumn(name = "video_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	VideoEntity video;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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

	public CommentEntity(String comment, UserEntity user, VideoEntity video) {
		super();
		this.comment = comment;
		this.user = user;
		this.video = video;
	}

	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
