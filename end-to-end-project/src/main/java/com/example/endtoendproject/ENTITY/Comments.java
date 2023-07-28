package com.example.endtoendproject.ENTITY;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comments {
@Id
private int comment_id;
@ManyToOne
@JoinColumn(name = "video_id")
private Video video;
private String comment_content;
public int getComment_id() {
	return comment_id;
}
public void setComment_id(int comment_id) {
	this.comment_id = comment_id;
}
public Video getVideo() {
	return video;
}
public void setVideo(Video video) {
	this.video = video;
}
public String getComment_content() {
	return comment_content;
}
public void setComment_content(String comment_content) {
	this.comment_content = comment_content;
}
public Comments(int comment_id, Video video, String comment_content) {
	super();
	this.comment_id = comment_id;
	this.video = video;
	this.comment_content = comment_content;
}
public Comments() {
	super();
	// TODO Auto-generated constructor stub
}

}
