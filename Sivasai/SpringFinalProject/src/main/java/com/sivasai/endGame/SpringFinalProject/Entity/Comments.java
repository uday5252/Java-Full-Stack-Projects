package com.sivasai.endGame.SpringFinalProject.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="videoId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Video videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userId;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Timestamp updated_at;

    public Comments(Video video_id, User user_id, String content) {
        this.videoId = video_id;
        this.userId = user_id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public Video getVideo_id() {
        return videoId;
    }

    public void setVideo_id(Video video_id) {
        this.videoId = video_id;
    }

    public User getUser_id() {
        return userId;
    }

    public void setUser_id(User user_id) {
        this.userId = user_id;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", video_id=" + videoId.toString() +
                ", user_id=" + userId.toStringForVideo() +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
