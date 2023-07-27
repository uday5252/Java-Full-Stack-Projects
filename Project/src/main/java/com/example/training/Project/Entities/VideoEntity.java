package com.example.training.Project.Entities;


import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Videos")
@NoArgsConstructor
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private @Getter @Setter String title;
    private @Getter @Setter String description;
    private @Getter @Setter String url;

    @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    private @Getter @Setter String uploaded_at;

    @ManyToOne
    @JoinColumn(name = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private @Getter @Setter UserEntity userId;


    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinTable(
    //     name = "Video_Genres",
    //     joinColumns = @JoinColumn(name = "video_id"),
    //     inverseJoinColumns = @JoinColumn(name = "genre_id")
    // )
    
    @ManyToOne
    @JoinColumn(name = "genreId")
    private @Getter @Setter GenreEntity genreId;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "videoId", fetch = FetchType.EAGER)
    private @Getter @Setter List<LikeEntity> likes;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "videoId", fetch = FetchType.EAGER)
    private @Getter @Setter List<CommentEntity> comments;


}
