package com.zee.Zee5Clone.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Genre")
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GenreId")
    private int id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id",referencedColumnName = "GenreId")
    private List<Video> videos = new ArrayList<>();
//    @ManyToMany
//    @JoinTable(name="video_genre",
//    joinColumns = @JoinColumn(name = "genre_id"),
//    inverseJoinColumns = @JoinColumn(name = "project_id"))
//    private Set<Video> videos2 = new HashSet<>();
//    public Genre(String name, String description, List<Video> videos) {
//        this.name = name;
//        this.description = description;
//        this.videos = videos;
//    }

    public Genre(String name, String description, List<Video> videos) {
        this.name = name;
        this.description = description;
        this.videos = videos;
    }
}
