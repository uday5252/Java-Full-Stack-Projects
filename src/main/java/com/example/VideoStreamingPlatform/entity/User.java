package com.example.VideoStreamingPlatform.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int user_id;
  private String username;
  private String password;
  private String email;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  @PrePersist
  public void PrePersist() {
    created_at = LocalDateTime.now();
    updated_at = LocalDateTime.now();
  }

}
