package com.zee.Zee5Clone.Controller;

import com.zee.Zee5Clone.Entity.User;
import com.zee.Zee5Clone.Service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/user")
    public void addUser(@RequestBody User user){
        userService.saveUser(user);
    }

}
