package com.zee.phani.project.ott.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Video Request Schema")
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
