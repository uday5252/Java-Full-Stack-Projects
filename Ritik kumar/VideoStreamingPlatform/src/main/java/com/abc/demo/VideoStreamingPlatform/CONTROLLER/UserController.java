package com.abc.demo.VideoStreamingPlatform.CONTROLLER;

import com.abc.demo.VideoStreamingPlatform.ENTITY.UserEntity;
import com.abc.demo.VideoStreamingPlatform.SERVICE.UserService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService us;

    @PostMapping("/api/admin/user")
    public ResponseEntity<String> addUser(@RequestBody UserEntity user)
    {
        us.createUser(user);
        return new ResponseEntity<>("user crerated successfully", HttpStatus.CREATED);
    }

    @GetMapping("/api/admin/users")
    public ResponseEntity<List<UserEntity>> showUsers()
    {
        return new ResponseEntity<>(us.showAllUser(),HttpStatus.OK);
    }
}
