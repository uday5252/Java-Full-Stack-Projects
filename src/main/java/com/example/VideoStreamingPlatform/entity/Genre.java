package com.example.VideoStreamingPlatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int genre_id;
  private String name;
  private String description;

  public Genre(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
