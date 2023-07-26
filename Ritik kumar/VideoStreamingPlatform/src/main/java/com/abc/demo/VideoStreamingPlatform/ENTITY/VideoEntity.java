package com.abc.demo.VideoStreamingPlatform.ENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoId;

    private String videoTitle;
    private String videoDescription;
    private String videoUrl;

//    @OneToMany(targetEntity = GenreEntity.class,cascade = CascadeType.ALL)
//    @JoinColumn(name="genre_id",referencedColumnName = "videoId")
//    private Set<GenreEntity> videoGenreId;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private GenreEntity genre;

    @Column(name = "uploaded_by")
    private int videoUploadedBy;

    private String videoUploadedAt;

    @OneToMany(targetEntity = LikeEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name="liked_video_id",referencedColumnName = "videoId")
    private List<LikeEntity> videoLikes;

    @OneToMany(targetEntity = CommentEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name="commented_video_id",referencedColumnName = "videoId")
    private List<CommentEntity> videoComments;

    public VideoEntity(String videoTitle, String videoDescription, String videoUrl, int videoUploadedBy) {
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.videoUrl = videoUrl;
        this.videoUploadedBy = videoUploadedBy;
    }
}
