package com.videostreamingapp.com.Assignment.project.ENTITY;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int like_id;

    @ManyToOne()
    @JoinColumn(name="video_id")
    private VideoEntity video;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime like_created_at;

    public LikeEntity(VideoEntity video, UserEntity user) {
        this.video = video;
        this.user = user;
    }

    public LikeEntity() {
    }

    public int getLike_id() {
        return like_id;
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
