package com.videostreamingapp.com.Assignment.project.SERVICE;

import com.videostreamingapp.com.Assignment.project.ENTITY.UserEntity;
import com.videostreamingapp.com.Assignment.project.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository ur;
    public void userCreated(UserEntity user) {
        ur.save(user);
    }

}
