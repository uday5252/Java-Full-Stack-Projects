package com.videostreamingapp.com.Assignment.project.ENTITY;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    private String comment_description;

    @ManyToOne()
    @JoinColumn(name="video_id")
    private VideoEntity video;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime like_created_at;

    public CommentEntity() {
    }

    public CommentEntity(String comment_description, VideoEntity video, UserEntity user) {
        this.comment_description = comment_description;
        this.video = video;
        this.user = user;
    }

    public int getComment_id() {
        return comment_id;
    }


    public String getComment_description() {
        return comment_description;
    }

    public void setComment_description(String comment_description) {
        this.comment_description = comment_description;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getLike_created_at() {
        return like_created_at;
    }

    public void setLike_created_at(LocalDateTime like_created_at) {
        this.like_created_at = like_created_at;
    }
}
