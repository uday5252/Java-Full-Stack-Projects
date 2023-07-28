package com.example.demo.End.project.ENTITY;

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
	int commentid;

	String comment;

	public String getComment() {
		return comment;
	}

	public CommentEntity(String comment, VideoEntity ve, UserEntity ue) {
		super();
		this.comment = comment;
		this.ve = ve;
		this.ue = ue;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName = "videoid")
	private VideoEntity ve;

	@ManyToOne
	@JoinColumn(referencedColumnName = "userid")
	private UserEntity ue;

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public VideoEntity getVe() {
		return ve;
	}

	public void setVe(VideoEntity ve) {
		this.ve = ve;
	}

	public UserEntity getUe() {
		return ue;
	}

	public void setUe(UserEntity ue) {
		this.ue = ue;
	}

//public CommentEntity(VideoEntity ve, UserEntity ue) {
//	super();
//	this.ve = ve;
//	this.ue = ue;
//}

	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
