package com.abc.demo.Video.Management.Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class genreEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int genreid;
	private String name;
	private String description;
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
	public genreEntity() {
		super();
	}
	public genreEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
}
