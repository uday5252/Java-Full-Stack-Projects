package com.zee.org.zee5_Clone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "commentTable")
public class CommentTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentid;

    private String Comment;

    @ManyToOne
    @JoinColumn(name="user_commented")
    private UserTable user;

    @ManyToOne
    @JoinColumn(name="video_gotcommented")
    private VideoTable video;


}
