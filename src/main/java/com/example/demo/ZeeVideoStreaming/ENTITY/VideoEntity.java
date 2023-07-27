package com.example.demo.ZeeVideoStreaming.ENTITY;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "videos")
public class VideoEntity {

	/*
	 * : `id`, `title`, `description`, `url`, `genre_id`, `uploaded_by`,
	 * `uploaded_at`
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String title;
	String description;
	String url;
	@CreationTimestamp
	String creationAt;
	@UpdateTimestamp
	String updatedAt;

	@ManyToOne
	@JoinColumn(name = "uploaded_by")
	@OnDelete(action = OnDeleteAction.CASCADE)
	UserEntity user;

	@ManyToOne
	@JoinColumn(name = "genre_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	GenreEntity genre;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCreationAt() {
		return creationAt;
	}

	public void setCreationDate(String creationDate) {
		this.creationAt = creationDate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public GenreEntity getGenre() {
		return genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public VideoEntity(String title, String description, String url, UserEntity user, GenreEntity genre) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.user = user;
		this.genre = genre;
	}

	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VideoEntity \n id=" + id + "\n title=" + title + "\n description=" + description + "\n url=" + url
				+ "\n creationAt=" + creationAt + "\n updatedAt=" + updatedAt + "\n user=" + user.getUserName()
				+ "\n genre=" + genre.getName();
	}

}
