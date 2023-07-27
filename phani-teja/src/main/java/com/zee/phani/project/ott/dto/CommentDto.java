package com.zee.phani.project.ott.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zee.phani.project.ott.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int userId;
    private int videoId;

    private String description;

    @JsonIgnore
    private CommentEntity comment;

    public String toString() {
        return "UserId:" + this.userId + "\tVideoId:" + this.videoId;
    }
}
