package com.videostreamingapp.com.Assignment.project.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int genre_id;
    private String genre_name;
    private String description;

    public GenreEntity(String genre_name, String description) {
        this.genre_name = genre_name;
        this.description = description;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public String getDescription() {
        return description;
    }

    public GenreEntity() {
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
