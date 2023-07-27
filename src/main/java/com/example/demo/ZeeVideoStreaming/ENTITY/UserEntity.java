package com.example.demo.ZeeVideoStreaming.ENTITY;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	/* `id`, `username`, `password`, `email`, `created_at`, `updated_at` */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String userName;
	String password;
	String email;
	@CreationTimestamp
	String created_at;
	@UpdateTimestamp
	String updated_at;

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserEntity(String userName, String password, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
