package com.videostreamingapp.com.Assignment.project.ENTITY;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int video_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity uploaded_by;

    private String title;
    private String description;
    private String url;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private GenreEntity genre;

    @CreationTimestamp
    private LocalDateTime uploaded_at;

    public VideoEntity(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public void setUploaded_by(UserEntity uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGenre_id(GenreEntity genre) {
        this.genre = genre;
    }

    public void setUploaded_at(LocalDateTime uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

    public int getVideo_id() {
        return video_id;
    }

    public UserEntity getUploaded_by() {
        return uploaded_by;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public GenreEntity getGenre_id() {
        return genre;
    }

    public LocalDateTime getUploaded_at() {
        return uploaded_at;
    }

    public VideoEntity() {
    }
}
