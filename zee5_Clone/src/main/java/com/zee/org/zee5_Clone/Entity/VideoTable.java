package com.zee.org.zee5_Clone.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "videoTable")
public class VideoTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoid;

    private String title;

    private String discription;

    private String url;

    @ManyToOne
    @JoinColumn(name = "genreid")
    private GenreTable genre;

    private int likes;

//    private List<String> comments;

    @ManyToOne
    @JoinColumn(name = "uploaded_by(userid)")
    private UserTable user;


    @CreationTimestamp
    private Instant uploaded_at;

}
