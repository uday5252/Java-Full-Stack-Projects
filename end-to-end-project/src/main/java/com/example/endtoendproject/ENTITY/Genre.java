package com.example.endtoendproject.ENTITY;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//Genre Table:
//- Columns: `id`, `name`, `description`
@Entity
public class Genre {
	@Id
	private int genreId;
//	@OneToMany(mappedBy = "genre")
//	private Set<Video> videos;
	private String name;
	private String description;
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
//	public Set<Video> getVideos() {
//		return videos;
//	}
//	public void setVideos(Set<Video> videos) {
//		this.videos = videos;
//	}
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
	public Genre(int genreId, Set<Video> videos, String name, String description) {
		super();
		this.genreId = genreId;
		//this.videos = videos;
		this.name = name;
		this.description = description;
	}
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
}
