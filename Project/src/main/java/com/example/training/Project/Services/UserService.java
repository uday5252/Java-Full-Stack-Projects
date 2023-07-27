package com.example.training.Project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training.Project.Entities.UserEntity;
import com.example.training.Project.Repositories.UserRepo;

@Service
public class UserService {
    
    @Autowired
    UserRepo uRepo;

    public void createUser(UserEntity user){
        System.out.println(user.getUsername());
        uRepo.save(user);
    }
}
