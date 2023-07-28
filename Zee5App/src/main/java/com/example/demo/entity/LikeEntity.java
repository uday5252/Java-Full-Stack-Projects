package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@NoArgsConstructor
@Entity
@AllArgsConstructor
@Setter
@Getter
@IdClass(LikeId.class)
public class LikeEntity {

    @Id
    @JoinColumn(name = "user_id",nullable = false)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private User userid;

    @Id
    @JoinColumn(name = "video_id",nullable = false)
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Video videoid;
    @CreationTimestamp
    private LocalDateTime created_at;


}
