package com.zee.phani.project.ott.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequestDto {
    private String name;
    private String description;

    public String toString() {
        return "Name:" + this.name + "\tDescription:" + this.description;
    }
}
