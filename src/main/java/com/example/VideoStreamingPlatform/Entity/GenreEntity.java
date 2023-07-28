package com.example.VideoStreamingPlatform.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class GenreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int genre_id;
	String name;
	String description;
	
//	@OneToMany(mappedBy="genre")
	public int getGenre_id() {
		return genre_id;
	}
	//	public void setId(int id) {
//		this.id = id;
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
	public GenreEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public GenreEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
