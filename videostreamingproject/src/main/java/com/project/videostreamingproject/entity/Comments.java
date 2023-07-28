package com.project.videostreamingproject.entity;

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
public class Comments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="user_id")
	private User u;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="video_id")
	private VideoTabel v;
	
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(User u, VideoTabel v, String comment) {
		super();
		this.u = u;
		this.v = v;
		Comment = comment;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public VideoTabel getV() {
		return v;
	}

	public void setV(VideoTabel v) {
		this.v = v;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	private String Comment;
}
