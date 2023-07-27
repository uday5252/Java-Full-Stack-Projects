package com.abc.demo.Final.Project.implementing.all.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comment")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commId;
	
	private String comment;
	
	@ManyToOne()
	@JoinColumn(name="usercom")
	private UserEntity usercom;
	
	@ManyToOne()
	@JoinColumn(name="videocom")
	private VideoEntity videocom;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public UserEntity getUsercom() {
		return usercom;
	}

	public void setUsercom(UserEntity usercom) {
		this.usercom = usercom;
	}

	public VideoEntity getVideocom() {
		return videocom;
	}

	public void setVideocom(VideoEntity videocom) {
		this.videocom = videocom;
	}

	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentEntity(String comment) {
		super();
		this.comment = comment;
	}
	
	
	
	
	
}
