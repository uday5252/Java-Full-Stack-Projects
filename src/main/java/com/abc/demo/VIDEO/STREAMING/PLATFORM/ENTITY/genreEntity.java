package com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class genreEntity {
//	`id`, `name`, `description`
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genreId;
	
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy = "genreID")
	private List<VideoEntity>Genrevideos;
	
	

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
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

	public genreEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public genreEntity() {
		super();
			}
	
	
	
}





//24e2f5e5f68a00c94ac3cb6dd7c9d93d api key
//https://api.themoviedb.org/3/movie/550?api_key=24e2f5e5f68a00c94ac3cb6dd7c9d93d