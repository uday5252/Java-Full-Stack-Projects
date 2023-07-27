package com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int like_id;

    @ManyToOne()
    @JoinColumn(name = "liked_by")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "liked_video")
    private VideoEntity video;

    private LocalDateTime liked_on;

}
