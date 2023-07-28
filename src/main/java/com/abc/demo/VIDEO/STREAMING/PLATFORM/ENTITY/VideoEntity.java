package com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY;



import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class VideoEntity {

//	`id`, `title`, `description`, `url`, `genre_id`, `uploaded_by`, `uploaded_at`
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	
	private String title;
	
	private String description;
	
	private String url;
	
	@Column(insertable = false, updatable = false)
	private int genreId;
	
	
	@Column(insertable = false, updatable = false)
	private int uploadedByUserId;
	
	@CreationTimestamp
	private String  uploadedAt;
	

	@OneToMany(mappedBy = "videolikes")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<likeEntity> likes;
	
	
	
	@OneToMany(mappedBy = "videocomments")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<commentEntity> comments;
	
	@ManyToOne
	@JoinColumn(name="genreId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private genreEntity genreID;
	
	
	@ManyToOne
	@JoinColumn(name="uploadedByUserId")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private userEntity userID;

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

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public int getUploadedByUserId() {
		return uploadedByUserId;
	}

	public void setUploadedByUserId(int uploadedByUserId) {
		this.uploadedByUserId = uploadedByUserId;
	}

	

	public genreEntity getGenreID() {
		return genreID;
	}

	public void setGenreID(genreEntity genreID) {
		this.genreID = genreID;
	}
	
	

	public userEntity getUserID() {
		return userID;
	}

	public void setUserID(userEntity userID) {
		this.userID = userID;
	}
	
	
	

	public VideoEntity(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public VideoEntity(String title, String description, String url, int uploadedByUserId) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.uploadedByUserId = uploadedByUserId;
	}

	public VideoEntity() {
		super();

	}

	public List<likeEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<likeEntity> likes) {
		this.likes = likes;
	}

	public List<commentEntity> getComments() {
		return comments;
	}

	public void setComments(List<commentEntity> comments) {
		this.comments = comments;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
