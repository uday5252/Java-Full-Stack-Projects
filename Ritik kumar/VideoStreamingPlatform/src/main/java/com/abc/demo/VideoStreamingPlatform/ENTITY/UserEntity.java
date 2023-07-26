package com.abc.demo.VideoStreamingPlatform.ENTITY;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userCreatedAt;

    private String userUpdatedAt;

    @OneToMany(targetEntity = VideoEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "uploaded_by",referencedColumnName = "user_id")
    private List<VideoEntity> videoList;

    public UserEntity(String userName,String userPassword,String userEmail)
    {
        this.userName=userName;
        this.userPassword=userPassword;
        this.userEmail=userEmail;
    }

}
