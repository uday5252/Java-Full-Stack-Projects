package com.example.VideoStreamingPlatform.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int video_id;
  private String title;
  @Column(length = 1000) 
  private String description;
  private String url;
  @ManyToOne
  @JoinColumn(name = "genre_id")
  private Genre genre;
  @ManyToOne
  @JoinColumn(name = "uploader_id")
  private User uploader;
  private LocalDateTime uploaded_at;

  public Video(String title, String description, String url) {
    this.title = title;
    this.description = description;
    this.url = url;
  }

  @PrePersist
  public void PrePersist() {
    uploaded_at = LocalDateTime.now();
  }
}
