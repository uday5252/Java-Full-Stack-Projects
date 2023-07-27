package com.zee.Zee5Clone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;
    @Column(name = "like_user_id")
    private int userId;
    @Column(name = "like_video_id")
    private int videoId;

    public LikeEntity(int userId, int videoId) {
        this.userId = userId;
        this.videoId = videoId;
    }
}
