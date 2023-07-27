package com.sam.demo.streaming.app.zee5.ENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String url;
    @CreationTimestamp
    private String uploaded_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="genre_id_id")
    private GenreEntity genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="uploaded_by")
    private UserEntity uploaded_by;

}
