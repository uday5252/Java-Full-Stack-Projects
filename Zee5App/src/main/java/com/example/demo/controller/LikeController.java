package com.example.demo.controller;

//import com.example.demo.entity.LikeEntity;
//import com.example.demo.service.LikeService;
import com.example.demo.entity.LikeEntity;
import com.example.demo.service.LikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Like Controller", description = "This controller controls all the endpoints associated with the LIKE functions related to the application")

@RestController
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping("/api/videos/like")
    public String likeTheVideo(@RequestBody LikeEntity likeEntity){
        likeService.collectLikeDetails(likeEntity);
        return "Liked the video";
    }



}
