package com.zee.phani.project.ott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.phani.project.ott.dto.UserRequestDto;
import com.zee.phani.project.ott.dto.UserResponseDto;
import com.zee.phani.project.ott.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Controller", description = "This controller controls all the endpoints associated with the User functions related to the application")

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Operation(summary = "Create User details", description = "This endpoint will take the user details and adds it to the Database.", responses = {
            @ApiResponse(responseCode = "201", description = "This response code corresponds to the sucessful insertion of data into the database."),
            @ApiResponse(responseCode = "206", description = "This response code is sent when there is partial content in the request body, please ensure all the fields have content before sending the request.")

    })
    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto inDao) {
        try {
            service.saveUserData(inDao);
            return new ResponseEntity<String>("Added Sucessfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.PARTIAL_CONTENT);
        }

    }

    @Operation(summary = "Delete User details", description = "This endpoint will take the user ID and deletes the user from the database", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful deletion of data from the application database."),
            @ApiResponse(responseCode = "400", description = "This response code is sent when there is an exception and the user with the given USER ID is not found in the application")

    })
    @DeleteMapping("user/delete/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable("uid") int userId) {
        try {
            service.deleteUser(userId);
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Fetch User details", description = "This endpoint will take the user ID and fetches the user details from the database", responses = {
            @ApiResponse(responseCode = "200", description = "This response code corresponds to the sucessful fetching of data from the application database."),
            @ApiResponse(responseCode = "404", description = "This response code is sent when there is an exception and the user with the given USER ID is not found in the application")

    })
    @GetMapping(value = "user/data/{uid}")
    public ResponseEntity<?> getUserEntity(@PathVariable("uid") int userId) {
        try {
            UserResponseDto user = service.getUser(userId);
            return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
