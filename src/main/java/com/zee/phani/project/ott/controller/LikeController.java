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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Like Controller", description = "This controller controls all the endpoints associated with the LIKE functions related to the application")

@RestController
public class LikeController {

    @Autowired
    LikeService service;

    @Operation(summary = "Create Like", description = "This endpoint will take the like details and adds it to the Database.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful insertion of data into the database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an internal error or there is a problem inserting into the database.")

    })
    @PostMapping("/like/create")
    public ResponseEntity<String> createLike(@RequestBody LikeDto inDto) {
        try {
            service.createLike(inDto);
            return new ResponseEntity<String>("Like Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get All Like details", description = "This endpoint will fetch all the Like details", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of all the likes from the database. The response can be null if there are no comments in the application database")
    })
    @GetMapping("/like/get")
    public ResponseEntity<List<LikeDto>> getLikes() {
        List<LikeDto> likes = service.getAllLikes();
        return new ResponseEntity<List<LikeDto>>(likes, HttpStatus.OK);
    }

    @Operation(summary = "Delete Like details", description = "This endpoint will take the like details and deletes the like from the database", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful deletion of data from the application database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an exception and the user with the given like details is not found in the application")

    })
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
