package com.example.VideoStreamingPlatform.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int comment_id;
  private String message;
  @ManyToOne
  @JoinColumn(name = "commentor")
  private User commentor;
  @ManyToOne
  @JoinColumn(name = "video_id")
  private Video video;
  private LocalDateTime created_at;

  @PrePersist
  public void PrePersist() {
    created_at = LocalDateTime.now();
  }
}
