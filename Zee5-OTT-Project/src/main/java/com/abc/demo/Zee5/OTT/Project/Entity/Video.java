package com.abc.demo.Zee5.OTT.Project.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Video {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int video_id;
		private String title;
		private String description;
		private String url;
		@ManyToOne()
		@JoinColumn(name="genre_id")
		@OnDelete(action = OnDeleteAction.CASCADE)
		private Genre genre;
		@ManyToOne()
		@JoinColumn(name="user_id")
		@OnDelete(action = OnDeleteAction.CASCADE)
		private User user;
		
		@CreationTimestamp
		private String uploaded_at;
		
		public int getVideo_id() {
			return video_id;
		}
		
		public void setVideo_id(int video_id) {
			this.video_id = video_id;
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
		
		public Genre getGenre() {
			return genre;
		}
		
		public void setGenre(Genre genre) {
			this.genre = genre;
		}
		
		public User getUser() {
			return user;
		}
		
		public void setUser(User user) {
			this.user = user;
		}
		
		public String getUploaded_at() {
			return uploaded_at;
		}
		
		public void setUploaded_at(String uploaded_at) {
			this.uploaded_at = uploaded_at;
		}
		
		public Video(String title, String description, String url) {
			super();
			this.title = title;
			this.description = description;
			this.url = url;
		}

		public Video() {
			super();
			// TODO Auto-generated constructor stub
		}
		






}
