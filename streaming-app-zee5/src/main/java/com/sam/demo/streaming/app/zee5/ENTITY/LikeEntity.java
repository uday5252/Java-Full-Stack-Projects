package com.sam.demo.streaming.app.zee5.ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private int videoId;

        private int userId;


    }


