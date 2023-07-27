package com.sam.demo.streaming.app.zee5.SERVICE;

import com.sam.demo.streaming.app.zee5.ENTITY.UserEntity;
import com.sam.demo.streaming.app.zee5.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository ur;

    public void addUser(UserEntity collectUserData){
        ur.save(collectUserData);
    }


}
