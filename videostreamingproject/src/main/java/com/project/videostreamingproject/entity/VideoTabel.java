package com.project.videostreamingproject.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VideoTabel {
     
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int videoId ;
	private String title;
	private String description;
	private String url;

	
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="genre_id")
	private Genre gid;
	
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="user_id")
	private User uploadedBy;
	public VideoTabel(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
	}
	public VideoTabel(String title, String description, String url, Genre gid, User uploadedBy) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.gid = gid;
		this.uploadedBy = uploadedBy;
	}
	public VideoTabel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Genre getGid() {
		return gid;
	}
	public void setGid(Genre gid) {
		this.gid = gid;
	}
	public User getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(User uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}
	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	private LocalDateTime uploadedAt;
	
}
