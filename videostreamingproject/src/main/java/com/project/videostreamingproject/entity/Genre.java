package com.project.videostreamingproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Genre {
   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int genreId ;
	private String genreName;
	private String genreDescription;
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getGenreDescription() {
		return genreDescription;
	}
	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
	}
	public Genre(String genreName, String genreDescription) {
		super();
		this.genreName = genreName;
		this.genreDescription = genreDescription;
	}
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
