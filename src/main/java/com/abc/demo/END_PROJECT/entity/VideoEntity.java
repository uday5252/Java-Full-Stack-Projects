package com.abc.demo.END_PROJECT.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="video")
public class VideoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String description;
	private String url;
	@ManyToOne
	@JoinColumn(referencedColumnName ="id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private GenreEntity genre_id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName ="id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private UserEntity uploaded_by;
	@CreationTimestamp
	private Timestamp uploaded_at;
	private int likescount;
	
	
	
	
	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VideoEntity(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		
	}
	public int getLikescount() {
		return likescount;
	}
	public void setLikescount(int likescount) {
		this.likescount = likescount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public GenreEntity getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(GenreEntity genre_id) {
		this.genre_id = genre_id;
	}
	public UserEntity getUploaded_by() {
		return uploaded_by;
	}
	public void setUploaded_by(UserEntity uploaded_by) {
		this.uploaded_by = uploaded_by;
	}
	public Timestamp getUploaded_at() {
		return uploaded_at;
	}
	public void setUploaded_at(Timestamp uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	
	
	

}
