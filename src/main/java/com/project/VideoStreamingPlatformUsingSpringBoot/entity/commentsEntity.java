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
@Table(name="comments")
public class commentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cmtId;
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private usersEntity user;
	@ManyToOne
	@JoinColumn(name="videoId",referencedColumnName = "videoId")
	private videosEntity video;
	private String comment;
	@CreationTimestamp
	private Timestamp commentedAt;
	
	public commentsEntity(String comment) {
		super();
		this.comment = comment;
	}
	public commentsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
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
	public Timestamp getCommentedAt() {
		return commentedAt;
	}
	public void setCommentedAt(Timestamp commentedAt) {
		this.commentedAt = commentedAt;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "commentsEntity [cmtId=" + cmtId + ", user=" + user + ", video=" + video + ", comment=" + comment
				+ ", commentedAt=" + commentedAt + "]";
	}
	
	
}
