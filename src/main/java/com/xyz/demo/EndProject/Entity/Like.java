package com.xyz.demo.EndProject.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_likes")
public class Like 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;
	@ManyToOne
	@JoinColumn(name="userId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;
	@ManyToOne
	@JoinColumn(name="videoId")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private VideoEntity video;
    @CreationTimestamp
    private String createdAt;
    
    
	
    public int getLikeId() {
		return likeId;
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
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public Like(UserEntity user, VideoEntity video, String createdAt) {
		super();
		this.user = user;
		this.video = video;
		this.createdAt = createdAt;
	}
	public Like() {
		super();
	}
	
	@Override
	public String toString() {
		return "Like [likeId=" + likeId + ", user=" + user + ", video=" + video + ", createdAt=" + createdAt + "]";
	}
	 
	
}
