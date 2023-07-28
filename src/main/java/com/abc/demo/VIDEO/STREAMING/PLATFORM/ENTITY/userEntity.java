package com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY;


import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class userEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String username;
	
	private String password;
	
	private String email;
	
	@CreationTimestamp
	private String created_at;
	@UpdateTimestamp
	private String updated_at;
	
	@OneToMany(mappedBy = "userID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<VideoEntity>UserVideos;
	
	
	@OneToMany(mappedBy = "userLiked")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<likeEntity>userLikes;
//	
////	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public userEntity(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	public userEntity() {
		super();
		
	}
	
	
	
	
	
	
	

	
	

}
