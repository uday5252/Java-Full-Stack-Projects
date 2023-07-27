package com.abc.Streaming.application.project.ENTITIES;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "video")
public class VideoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private String url;
	@ManyToOne
	@JoinColumn(name = "gid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private GenreEntity genreId;
	
	
	@UpdateTimestamp
	private String uploadedAt;
	@ManyToOne
	@JoinColumn(name = "uid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserEntity userId;

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

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	public GenreEntity getGenreId() {
		return genreId;
	}

	public void setGenreId(GenreEntity genreId) {
		this.genreId = genreId;
	}

	public VideoEntity(String title, String description, String url, UserEntity userId, GenreEntity genreId) {
		super();

		this.title = title;
		this.description = description;
		this.url = url;
		this.userId = userId;
		this.genreId = genreId;
	}

	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VideoEntity [id=" + id + ", title=" + title + ", description=" + description + ", url=" + url
				+ ", genreId=" + genreId + ", uploadedAt=" + uploadedAt + ", userId=" + userId + "]";
	}

}
