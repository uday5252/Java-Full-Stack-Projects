package com.project.VideoStreamingPlatformUsingSpringBoot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genres")
public class genresEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genreId;
	private String genreName;
	private String description;
	public genresEntity(String genreName, String description) {
		super();
		this.genreName = genreName;
		this.description = description;
	}
	public genresEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "genresEntity [genreId=" + genreId + ", genreName=" + genreName + ", description=" + description + "]";
	}
	
}
