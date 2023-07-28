package com.zee.phani.project.ott.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User Request Schema")
public class UserRequestDto {
    private String userName;
    private String password;
    private String email;
}
