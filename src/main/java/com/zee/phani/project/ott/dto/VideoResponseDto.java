package com.zee.phani.project.ott.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zee.phani.project.ott.entity.VideoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponseDto {
    private int id;
    private String title;
    private String description;
    private String genreName;
    private String url;

    @JsonIgnore
    private VideoEntity video;
}
