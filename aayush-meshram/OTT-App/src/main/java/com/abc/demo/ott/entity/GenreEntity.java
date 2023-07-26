package com.abc.demo.ott.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "genre_id")
    private int genreID;

    private String genreName;

    private String genreDescription;

    @ManyToMany
//    @JsonIgnore
    private Set<VideoEntity> videoEntitySet;

    public GenreEntity(String genreName, String genreDescription)  {
        this.genreName = genreName;
        this.genreDescription = genreDescription;
    }
}
