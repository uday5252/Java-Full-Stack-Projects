package com.abc.demo.Zee5.Apache.Kafka.Producer.CONTROLLER;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.UserEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<String> insertUser(@RequestBody UserEntity user){
        userService.addUser(user);
        return new ResponseEntity<String>("User Created", HttpStatus.CREATED);
    }
}
