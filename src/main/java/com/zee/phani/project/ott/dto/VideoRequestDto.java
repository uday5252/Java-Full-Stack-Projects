package com.zee.phani.project.ott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoRequestDto {

    private String title;
    private String description;
    private String url;
    private int userId;
    private int genreId;

    public String toString() {
        return "Title:" + this.title + "\tDescription" + this.description + "\tUrl:" + this.url;
    }
}
