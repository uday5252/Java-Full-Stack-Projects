package com.zee.phani.project.ott.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zee.phani.project.ott.entity.GenreEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponseDto {
    private int id;
    private String name;
    private String description;

    @JsonIgnore
    private GenreEntity genre;
}
