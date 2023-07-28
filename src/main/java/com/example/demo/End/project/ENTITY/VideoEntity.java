package com.example.demo.End.project.ENTITY;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VideoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int videoid;
	private String title;
	private String description;
	private String url;
	
	int LikesCount;
	public int getLikesCount() {
		return LikesCount;
	}
	public void setLikesCount(int likesCount) {
		LikesCount = likesCount;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="genreid")
	private GenreEntity genre;
	
	
	@ManyToOne
	@JoinColumn(referencedColumnName="userid")
	private UserEntity uploadedBy;
	
	public UserEntity getUser() {
		return uploadedBy;
	}
	public void setUser(UserEntity uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	
    @CreationTimestamp
    private Instant uploadedAt;
	public int getVideoid() {
		return videoid;
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
	public GenreEntity getGenre() {
		return genre;
	}
	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}
	
	
	public Instant getUploadedAt() {
		return uploadedAt;
	}
	public void setUploadedAt(Instant uploadedAt) {
		this.uploadedAt = uploadedAt;
	}
	public VideoEntity(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
	
		
	
	}
	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}
