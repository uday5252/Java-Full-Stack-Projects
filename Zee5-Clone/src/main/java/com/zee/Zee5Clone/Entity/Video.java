package com.zee.Zee5Clone.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Video")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String url;

    //forighn key mapping

    @Column(name = "genre_id")
    private Integer genreId;
    @Column(name = "uploadedBy")
    private int uploadedBy;
    private String uploadedAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_video_id",referencedColumnName = "id")
    private List<CommentEntity> comments = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="like_video_id",referencedColumnName = "id")
    private List<LikeEntity> likes = new ArrayList<>();
//    @JsonIgnore
//    @ManyToMany(mappedBy = "videos2")
//    private Set<Genre> genres = new HashSet<>();

//    public Video(String title, String description, String url, String uploadedAt, Set<Genre> genres) {
//        this.title = title;
//        this.description = description;
//        this.url = url;
//        this.uploadedAt = uploadedAt;
//        this.genres = genres;
//    }

    public Video(String title, String description, String url, int genreId, int uploadedBy, String uploadedAt) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.genreId = genreId;
        this.uploadedBy = uploadedBy;
        this.uploadedAt = uploadedAt;
    }

}
