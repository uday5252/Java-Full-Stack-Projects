package com.zee.phani.project.ott.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zee.phani.project.ott.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private int userId;
    private String userName;
    private String email;
    private String password;

    @JsonIgnore
    private UserEntity user;
}
