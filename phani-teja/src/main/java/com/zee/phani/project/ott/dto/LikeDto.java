package com.zee.phani.project.ott.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zee.phani.project.ott.entity.LikeEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Like Schema")
public class LikeDto {
    private int userId;
    private int videoId;

    @JsonIgnore
    private LikeEntity like;

    public String toString() {
        return "UserId:" + this.userId + "\tVideoId:" + this.videoId;
    }
}
