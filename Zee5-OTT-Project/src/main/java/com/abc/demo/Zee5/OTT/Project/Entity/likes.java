package com.abc.demo.Zee5.OTT.Project.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int like_id;
	
	@ManyToOne
	@JoinColumn(name="video_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Video video;
	@ManyToOne
	@JoinColumn(name="user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	public int getLike_id() {
		return like_id;
	}
	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public likes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
