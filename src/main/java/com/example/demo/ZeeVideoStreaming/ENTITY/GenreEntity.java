package com.example.demo.ZeeVideoStreaming.ENTITY;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class GenreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Column(unique=true)
	String name;
	String description;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	UserEntity user;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUser() {
		return user;
	}

	public GenreEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public GenreEntity(String name, String description, UserEntity user) {
		super();
		this.name = name;
		this.description = description;
		this.user = user;
	}

}
