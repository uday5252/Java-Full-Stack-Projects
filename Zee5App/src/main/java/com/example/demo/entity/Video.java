package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Video{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vid;

    private String title;
    private String description;
    private String url;
    @JoinColumn(name = "genre_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    private Genre genre_id;

    @JoinColumn(name="uploaded_by",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
    private User uploaded_by;
    @CreationTimestamp
    private LocalDateTime uploaded_at;

}
