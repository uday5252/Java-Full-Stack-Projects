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

import com.zee.phani.project.ott.dto.CommentDto;
import com.zee.phani.project.ott.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService service;

    @PostMapping("/Comment/create")
    public ResponseEntity<String> createComment(@RequestBody CommentDto inDto) {
        try {
            service.createComment(inDto);
            return new ResponseEntity<String>("Comment Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Comment/get")
    public ResponseEntity<List<CommentDto>> getComments() {
        List<CommentDto> Comments = service.getAllComments();
        return new ResponseEntity<List<CommentDto>>(Comments, HttpStatus.OK);
    }

    @DeleteMapping("/Comment/delete")
    public ResponseEntity<String> deleteComment(@RequestBody CommentDto inDto) {
        try {
            service.deleteComment(inDto);
            return new ResponseEntity<String>("Comment Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
