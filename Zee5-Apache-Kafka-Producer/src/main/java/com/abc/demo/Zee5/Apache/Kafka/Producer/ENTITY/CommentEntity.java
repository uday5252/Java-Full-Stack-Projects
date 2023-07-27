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
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;
    private String comment;

    @ManyToOne
    @JoinColumn(name="commented_by")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "Commented_video")
    private VideoEntity video;

    private LocalDateTime commented_on;
}
