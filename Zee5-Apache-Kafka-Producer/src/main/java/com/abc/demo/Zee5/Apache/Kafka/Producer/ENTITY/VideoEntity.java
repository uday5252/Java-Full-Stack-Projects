package com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity(name = "Video_Table")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int video_id;
    private String video_title;
    private String video_description;
    private String video_url;

    @ManyToOne()
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;

    @ManyToOne()
    @JoinColumn(name = "uploaded_by")
    private UserEntity user;

    private LocalDateTime uploaded_at;

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_description() {
        return video_description;
    }

    public void setVideo_description(String video_description) {
        this.video_description = video_description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public GenreEntity getGenre_id() {
        return genre;
    }

    public void setGenre_id(GenreEntity genre) {
        this.genre = genre;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getUploaded_at() {
        return uploaded_at;
    }

    public void setUploaded_at(LocalDateTime uploaded_at) {
        this.uploaded_at = uploaded_at;
    }

    public VideoEntity(String video_title, String video_description, String video_url) {
        this.video_title = video_title;
        this.video_description = video_description;
        this.video_url = video_url;
    }

    public VideoEntity(){
    }
}
