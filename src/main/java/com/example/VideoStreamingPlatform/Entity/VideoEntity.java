package com.example.VideoStreamingPlatform.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VideoEntity {
 //`id`, `title`, `description`, `url`, `genre_id`, `uploaded_by`, `uploaded_at`
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int video_id;
	String title;
	String description;
	String url;
	
	@ManyToOne
	@JoinColumn(name = "genre_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	GenreEntity genre;
	String uploaded_by;
	@CreationTimestamp
	String uploaded_at;
	public int getId() {
		return video_id;
	}
//	public void setId(int id) {
//		this.id = id;
//	}
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
	public String getUploaded_by() {
		return uploaded_by;
	}
	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}
	public String getUploaded_at() {
		return uploaded_at;
	}
//	public void setUploaded_at(String uploaded_at) {
//		this.uploaded_at = uploaded_at;
//	}
	public VideoEntity(String title, String description, String url, GenreEntity genre, String uploaded_by) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.genre = genre;
		this.uploaded_by = uploaded_by;
	}
	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
