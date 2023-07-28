package com.xyz.demo.EndProject.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="videoTable")
public class VideoEntity 
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int videoId;
	 private String title;
	 private String description;
	 private String url;
	 @ManyToOne
	 @JoinColumn(referencedColumnName = "genreId")
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 private GenreEntity genreId;
	 @ManyToOne
	 @JoinColumn(referencedColumnName = "userId")
	 @OnDelete(action = OnDeleteAction.CASCADE)
	 private UserEntity uploaded_by;
	 @UpdateTimestamp
	 private String uploaded_at;
	
	 
	public int getVideoId() {
		return videoId;
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
	public GenreEntity getGenreId() {
		return genreId;
	}
	public void setGenreId(GenreEntity genreId) {
		this.genreId = genreId;
	}
	public UserEntity getUploaded_by() {
		return uploaded_by;
	}
	public void setUploaded_by(UserEntity user) {
		this.uploaded_by = user;
	}
	public String getUploaded_at() {
		return uploaded_at;
	}
	public void setUploaded_at(String uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	public VideoEntity(String title, String description, String url, GenreEntity genreId, UserEntity uploaded_by,
			String uploaded_at) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.genreId = genreId;
		this.uploaded_by = uploaded_by;
		this.uploaded_at = uploaded_at;
	}
	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "VideoEntity [videoId=" + videoId + ", title=" + title + ", description=" + description + ", url=" + url
				+ ", genreId=" + genreId + ", uploaded_by=" + uploaded_by + ", uploaded_at=" + uploaded_at + "]";
	}
	 
	 
}
