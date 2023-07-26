package com.abc.demo.VideoStreamingPlatform.ENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="genre_id")
    private int genreId;

    private String genreName;

    private String genreDescription;

    public GenreEntity(String genreName,String genreDescription)
    {
        this.genreName=genreName;
        this.genreDescription=genreDescription;
    }

}
