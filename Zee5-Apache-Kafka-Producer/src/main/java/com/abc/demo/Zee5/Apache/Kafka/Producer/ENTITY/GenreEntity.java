package com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "genre_table")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;
    private String genre_name;
    private String genre_description;

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public String getGenre_description() {
        return genre_description;
    }

    public void setGenre_description(String genre_description) {
        this.genre_description = genre_description;
    }

    public GenreEntity(String genre_name, String genre_description) {
        this.genre_name = genre_name;
        this.genre_description = genre_description;
    }

    public GenreEntity(){

    }
}
