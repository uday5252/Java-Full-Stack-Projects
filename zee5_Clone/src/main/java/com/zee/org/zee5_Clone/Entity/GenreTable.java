package com.zee.org.zee5_Clone.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "genreTable")
public class GenreTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreid;

    private String name;

    private String discription;




}
