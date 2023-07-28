package com.example.demo.End.project.ENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="genreid")
    int genreid;
    String genrename;
    String description;
	public int getId() {
		return genreid;
	}
	public String getName() {
		return genrename;
	}
	public void setName(String name) {
		this.genrename = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GenreEntity(String name, String description) {
		super();
		this.genrename = name;
		this.description = description;
	}
	public GenreEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
