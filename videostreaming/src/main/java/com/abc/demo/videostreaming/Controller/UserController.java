package com.abc.demo.videostreaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    void addUser(@RequestBody User user){
        us.save(user);
    }

    @DeleteMapping("/api/delete")
    void deleteUser(int id){
        us.delete(uri.getReferenceById(id));
    }

}
