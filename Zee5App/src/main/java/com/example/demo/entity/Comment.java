package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(LikeId.class)
public class Comment {
    @Id
    @JoinColumn(name = "user_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    private User userid;
    @Id
    @JoinColumn(name = "video_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    private Video videoid;
    private String description;
    @CreationTimestamp
    private LocalDateTime created_at;
}
