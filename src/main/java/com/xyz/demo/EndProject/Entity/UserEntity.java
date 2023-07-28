package com.xyz.demo.EndProject.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userTable")
public class UserEntity 
{	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String password;
	private String email;
	@CreationTimestamp
	private String created_at;
	@UpdateTimestamp
	private String updated_at;
	
	
	public int getUserId() {
		return userId;
	}
	public String getCreated_at() {
		return created_at;
	}
	public String getUpdated_at() {
		return updated_at;
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
//	public String getCreated_at() {
//		return created_at;
//	}
//	public String getUpdated_at() {
//		return updated_at;
//	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public void setCreated_at(String created_at) {
//		this.created_at = created_at;
//	}
//	public void setUpdated_at(String updated_at) {
//		this.updated_at = updated_at;
//	}
	public UserEntity(String userName, String password, String email, String created_at, String updated_at) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
