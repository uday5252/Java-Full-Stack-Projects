package com.abc.demo.Video.Management.Project.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class userEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;
	private String username;
	private String password;
	private String email;
	@CreationTimestamp
	private String created_At;
	@UpdateTimestamp
	private String updated_At;
	@Override
	public String toString() {
		return "userEntity [userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", created_At=" + created_At + ", updated_At=" + updated_At + "]";
	}
	public int getUserid() {
		return userid;
	}
	public String getCreated_At() {
		return created_At;
	}
	public String getUpdated_At() {
		return updated_At;
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
