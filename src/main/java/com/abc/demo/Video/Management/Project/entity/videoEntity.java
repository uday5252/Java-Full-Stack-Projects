package com.abc.demo.Video.Management.Project.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class videoEntity {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int videoid;
	private String title;
	private String description;
	private String url;
	@UpdateTimestamp
	private String updated_At;

	@ManyToOne
	@JoinColumn(name = "user_Id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private userEntity user;
	
	@ManyToOne
	@JoinColumn(name = "genre_Id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private genreEntity genre;

	
	public int getVideoid() {
		return videoid;
	}

	public String getUpdated_At() {
		return updated_At;
	}

	public userEntity getUser() {
		return user;
	}

	public genreEntity getGenre() {
		return genre;
	}
	public void setUser(userEntity user) {
		this.user = user;
	}

	public void setGenre(genreEntity genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public videoEntity(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public videoEntity() {
		super();
	}
	
}
