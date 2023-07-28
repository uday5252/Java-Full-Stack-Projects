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
@Table(name="ratings")
public class ratingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingId;
	private float rating;
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private usersEntity user;
	@ManyToOne
	@JoinColumn(name="videoId",referencedColumnName = "videoId")
	private videosEntity video;
	@CreationTimestamp
	private Timestamp ratedAt;
	
	public int getRatingId() {
		return ratingId;
	}
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public usersEntity getUser() {
		return user;
	}
	public void setUser(usersEntity user) {
		this.user = user;
	}
	public videosEntity getVideo() {
		return video;
	}
	public void setVideo(videosEntity video) {
		this.video = video;
	}
	public Timestamp getRatedAt() {
		return ratedAt;
	}
	public void setRatedAt(Timestamp ratedAt) {
		this.ratedAt = ratedAt;
	}
	public ratingEntity(int rating) {
		super();
		this.rating = rating;
	}
	public ratingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
