package com.example.VideoStreamingPlatform.Entity;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int user_id;
	String username;
	String password;
	String email;
	@CreationTimestamp
	String created_at;
	@CreationTimestamp
	String updated_at;
	public int getId() {
		return user_id;
	}
//	public void setId(int id) {
//		this.id = id;
//	}
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public UserEntity(String username, String password, String email, String created_at, String updated_at) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
//		this.created_at = created_at;
//		this.updated_at = updated_at;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
