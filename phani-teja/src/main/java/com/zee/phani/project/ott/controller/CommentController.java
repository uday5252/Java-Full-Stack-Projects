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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Comment Controller", description = "This controller controls all the endpoints associated with the COMMENT functions related to the application")

@RestController
public class CommentController {

    @Autowired
    CommentService service;

    @Operation(summary = "Create Comment", description = "This endpoint will take the comment details and adds it to the Database.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful insertion of data into the database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem inserting into the database.")

    })
    @PostMapping("/Comment/create")
    public ResponseEntity<String> createComment(@RequestBody CommentDto inDto) {
        try {
            service.createComment(inDto);
            return new ResponseEntity<String>("Comment Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get All Comment details", description = "This endpoint will fetch all the comments details", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of all the comments from the database. The response can be null if there are no comments in the application database")
    })
    @GetMapping("/Comment/get")
    public ResponseEntity<List<CommentDto>> getComments() {
        List<CommentDto> Comments = service.getAllComments();
        return new ResponseEntity<List<CommentDto>>(Comments, HttpStatus.OK);
    }

    @Operation(summary = "Delete Comment details", description = "This endpoint will take the Comment details and deletes the like from the database", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful deletion of data from the application database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an exception and the user with the given comment details is not found in the application")

    })
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
