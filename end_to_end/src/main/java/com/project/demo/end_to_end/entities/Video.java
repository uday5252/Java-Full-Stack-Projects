package com.project.demo.end_to_end.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private int id;
    private String title;
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private Genre genre;
    @ManyToOne
    @JoinColumn(name = "uploaded_by_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Schema(hidden = true)
    private User uploadedBy;
    @Schema(hidden = true)
    @CreationTimestamp
    private LocalDateTime uploadedAt;
    @Override
    public String toString() {
        return "Video [id=" + id + ", title=" + title + ", description=" + description + ", url=" + url + ", genre="
                + genre + ", uploadedBy=" + uploadedBy + ", uploadedAt=" + uploadedAt + "]";
    }
    
}
