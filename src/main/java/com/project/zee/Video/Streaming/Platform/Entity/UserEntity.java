package com.project.zee.Video.Streaming.Platform.Entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
	
//	- Columns: `id`, `username`, `password`, `email`, `created_at`, `updated_at`
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Column(name="created_at")
	@CreationTimestamp
	private Instant created_at;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Instant updated_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserEntity(String username, String password, String email) {
		super();

		this.username = username;
		this.password = password;
		this.email = email;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}