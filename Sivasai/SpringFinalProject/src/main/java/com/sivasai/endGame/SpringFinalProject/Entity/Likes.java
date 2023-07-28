package com.sivasai.endGame.SpringFinalProject.Entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="video_id", referencedColumnName = "id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Video videoId;

    public Likes(User user_id, Video video_id) {
        this.userId = user_id;
        this.videoId = video_id;
    }

    public int getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Video getVideoId() {
        return videoId;
    }

    public void setVideoId(Video videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", user_id=" + userId.toStringForVideo() +
                ", video_id=" + videoId.toString() +
                '}';
    }
}
