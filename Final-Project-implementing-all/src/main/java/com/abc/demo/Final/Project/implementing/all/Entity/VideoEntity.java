package com.abc.demo.Final.Project.implementing.all.Entity;

import org.hibernate.annotations.CreationTimestamp;

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
	private int vid;
	private String title;
	private String Vdescription;
	private String url;
	
	@ManyToOne()
	@JoinColumn(name="genre_id")
	private GenreEntity genre;
	
	@ManyToOne()
	@JoinColumn(name="uploaded_by")
	private UserEntity uploaded_by;
	
	@CreationTimestamp
	private String uploaded_at;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVdescription() {
		return Vdescription;
	}

	public void setVdescription(String vdescription) {
		Vdescription = vdescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public GenreEntity getGenre() {
		return genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public UserEntity getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(UserEntity uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public VideoEntity(String title, String vdescription, String url) {
		super();
		this.title = title;
		Vdescription = vdescription;
		this.url = url;
	}

	public VideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
}
