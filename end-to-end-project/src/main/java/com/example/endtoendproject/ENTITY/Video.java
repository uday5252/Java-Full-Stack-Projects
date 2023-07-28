package com.example.endtoendproject.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//Video Table:
//- Columns: `id`, `title`, `description`, `url`, `genre_id`, `uploaded_by`, `uploaded_at`
@Entity
public class Video {
	@Id
	private int videoId;
	private String title;
	private String description;
	private String url;
	@ManyToOne
	@JoinColumn(name="genre_Id")
	private Genre genre;
	private String uploaded_by;
	private String uploaded_at;
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
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
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
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
	public void setUploaded_at(String uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	public Video(int videoId, String title, String description, String url, Genre genre, String uploaded_by,
			String uploaded_at) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.description = description;
		this.url = url;
		this.genre = genre;
		this.uploaded_by = uploaded_by;
		this.uploaded_at = uploaded_at;
	}
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
}
