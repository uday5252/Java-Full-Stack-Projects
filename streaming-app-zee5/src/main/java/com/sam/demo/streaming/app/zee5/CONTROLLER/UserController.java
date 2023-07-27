package com.sam.demo.streaming.app.zee5.CONTROLLER;

import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.SERVICE.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService us;

    @PostMapping("user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity addUser(@RequestBody UserEntity ue){
        us.addUser(ue);
        return ue;

    }






}
