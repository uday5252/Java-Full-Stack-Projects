package com.zee.phani.project.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.phani.project.ott.dto.LikeDto;
import com.zee.phani.project.ott.service.LikeService;

@RestController
public class LikeController {

    @Autowired
    LikeService service;

    @PostMapping("/like/create")
    public ResponseEntity<String> createLike(@RequestBody LikeDto inDto) {
        try {
            service.createLike(inDto);
            return new ResponseEntity<String>("Like Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/like/get")
    public ResponseEntity<List<LikeDto>> getLikes() {
        List<LikeDto> likes = service.getAllLikes();
        return new ResponseEntity<List<LikeDto>>(likes, HttpStatus.OK);
    }

    @DeleteMapping("/like/delete")
    public ResponseEntity<String> deleteLike(@RequestBody LikeDto inDto) {
        try {
            service.deleteLike(inDto);
            return new ResponseEntity<String>("Like Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
