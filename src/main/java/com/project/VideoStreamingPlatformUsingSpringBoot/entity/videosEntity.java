package com.project.VideoStreamingPlatformUsingSpringBoot.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="videos")
public class videosEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	private String title;
	private String description;
	private String url;
	@ManyToOne
	@JoinColumn(name="genreId",referencedColumnName = "genreId")
	private genresEntity genre;
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "userId")
	private usersEntity user;
	private int likesCount;
	private int comments;
	private float rating;
	private int usersrated;
	@CreationTimestamp
	private Timestamp uploadedAt;
	
	

	public videosEntity(String title, String description, String url, genresEntity genre, usersEntity user) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.genre = genre;
		this.user = user;
	}

	public videosEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public genresEntity getGenre() {
		return genre;
	}

	public void setGenre(genresEntity genre) {
		this.genre = genre;
	}

	public usersEntity getUser() {
		return user;
	}

	public void setUser(usersEntity user) {
		this.user = user;
	}

	public Timestamp getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(Timestamp uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public int getLikesCount() {
		return likesCount;
	}

	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getUsersrated() {
		return usersrated;
	}
	
	public void setUsersrated(int usersrated) {
		this.usersrated = usersrated;
	}

	@Override
	public String toString() {
		return "videosEntity [videoId=" + videoId + ", title=" + title + ", description=" + description + ", url=" + url
				+ ", genre=" + genre + ", user=" + user + ", likesCount=" + likesCount + ", comments=" + comments
				+ ", rating=" + rating + ", usersrated=" + usersrated + ", uploadedAt=" + uploadedAt + "]";
	}
	
	
	
	
}
