package com.abc.demo.videostreaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.videostreaming.Entity.User;
import com.abc.demo.videostreaming.EntityRepository.UserInterfaceRepository;
import com.abc.demo.videostreaming.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "User Controller",
    description = "User Controller"
)
@RestController
public class UserController {

    @Autowired
    UserInterfaceRepository uri;

    @Autowired
    UserService us;
    
    @Operation(
        description = "Add User",
        summary = "Add User"
    )
    @PostMapping("/api/adduser")
    ResponseEntity<String> addUser(@RequestBody User user){
        us.save(user);
        return new ResponseEntity<String>("User Added",HttpStatus.OK);
    }

    @Operation(
        description = "Delete User",
        summary = "Delete User"
    )
    @DeleteMapping("/api/delete")
    ResponseEntity<String> deleteUser(int id){
        us.delete(uri.getReferenceById(id));
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

}
