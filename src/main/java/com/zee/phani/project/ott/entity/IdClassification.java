package com.zee.phani.project.ott.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class IdClassification implements Serializable {

    private UserEntity user;
    private VideoEntity video;

}
