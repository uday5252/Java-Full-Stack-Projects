package com.zee.Zee5Clone.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comments")
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;
    private String comment;
    @Column(name = "comment_user_id")
    private int userId;

    @Column(name = "comment_video_id")
    private int videoId;

    public CommentEntity(String comment, int userId, int videoId) {
        this.comment = comment;
        this.userId = userId;
        this.videoId = videoId;
    }
}
