package com.project.zee.Video.Streaming.Platform.Entity;

import java.time.Instant;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VideoEntity {
	
//	Columns: `id`, `title`, `description`, `url`, `genre_id`, `uploaded_by`, `uploaded_at`
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn (name="genre_id")
	private GenreEntity genre;
	
	private String title ;
	private String description;
	private String url;
	
	@ManyToOne
	@JoinColumn (name="uploaded_by")
	private UserEntity user;
	
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private Instant updated_at;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public GenreEntity getGenre() {
		return genre;
	}


	public void setGenre(GenreEntity genre) {
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


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public VideoEntity(GenreEntity genre, String title, String description, String url, UserEntity user) {
		super();
		this.genre = genre;
		this.title = title;
		this.description = description;
		this.url = url;
		this.user = user;
	}


	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

}
