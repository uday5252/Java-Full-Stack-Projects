package com.sivasai.endGame.SpringFinalProject.Controller;

import com.sivasai.endGame.SpringFinalProject.Entity.Roles;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.ResponseGenerator.ResponseHandler;
import com.sivasai.endGame.SpringFinalProject.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        user.tokenSet(null);
        user.roleSet(Roles.ROLE_USER);
        User u = userService.saveUser(user);
        return ResponseHandler.generateResponse("User created successfully", HttpStatus.CREATED, u);
    }
}
