package com.zee.org.zee5_Clone.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "likeTable")
public class LikeTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeid;

    @ManyToOne
    @JoinColumn(name="user_liked")
    private UserTable userid;

    @ManyToOne
    @JoinColumn(name="video_liked")
    private VideoTable videoid;

}
