package com.example.endtoendproject.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Likes {
@Id
private int like_id;
@ManyToOne
@JoinColumn(name = "video_id")
private Video video;
public int getLike_id() {
	return like_id;
}
public void setLike_id(int like_id) {
	this.like_id = like_id;
}
public Video getVideo() {
	return video;
}
public void setVideo(Video video) {
	this.video = video;
}
public Likes(int like_id, Video video) {
	super();
	this.like_id = like_id;
	this.video = video;
}
public Likes() {
	super();
	// TODO Auto-generated constructor stub
}

}
