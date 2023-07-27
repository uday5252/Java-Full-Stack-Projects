package com.zee.org.zee5_Clone.Controller;



import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{

    @Autowired
    UserService Us;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserTable>> viewAllusers(){
        List<UserTable> li= Us.ViewAllUsers();
        return new ResponseEntity<>(li, HttpStatus.OK);
    }
    @PostMapping("/api/admin/users")
    public ResponseEntity<String> addUser(@RequestBody UserTable user){
        Us.createUser(user);
        return new ResponseEntity<>("Updated User", HttpStatus.CREATED);

    }
    @PutMapping("/api/admin/users/{userId}")
    public ResponseEntity<UserTable> updateUser(@PathVariable("userId")int a,UserTable user){
        UserTable data=Us.updateUser(a,user);
        return new ResponseEntity<>(data, HttpStatus.OK);

    }


    @DeleteMapping("/api/admin/users/{userId}")
    public ResponseEntity<String> delUser(@PathVariable("userId") int a){
        Us.DeleteUser(a);
        return new ResponseEntity<>("Deleted User",HttpStatus.OK);
    }
}
