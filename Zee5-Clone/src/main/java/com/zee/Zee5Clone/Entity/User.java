package com.zee.Zee5Clone.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int id;
    private String username;
    private String password;
    private String email;
    private String createdAt;
    private String updatedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadedBy",referencedColumnName = "UserId")
    private List<Video> videos = new ArrayList<>();

    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "comment_user_id",referencedColumnName = "UserId")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_user_id",referencedColumnName = "UserId")
    private List<LikeEntity> likes = new ArrayList<>();

    public User(String username, String password, String email, String createdAt, String updatedAt, List<Video> videos) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.videos = videos;
    }
}
