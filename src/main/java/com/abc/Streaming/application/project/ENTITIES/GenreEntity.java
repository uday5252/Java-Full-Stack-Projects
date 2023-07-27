package com.abc.Streaming.application.project.ENTITIES;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "genre")
public class GenreEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;

    @JsonIgnore
	public int getId() {
		return id;
	}

    @JsonIgnore
	public void setId(int id) {
		this.id = id;
	}
	public GenreEntity(int id) {
		super();
		this.id = id;
	}

	public GenreEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GenreEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

}
