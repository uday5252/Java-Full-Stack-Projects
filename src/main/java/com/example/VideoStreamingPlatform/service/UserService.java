package com.example.VideoStreamingPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VideoStreamingPlatform.entity.User;
import com.example.VideoStreamingPlatform.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository ur;

  public User createUser(User newUser) {
    ur.save(newUser);
    return ur.findById(newUser.getUser_id()).get();
  }
}
