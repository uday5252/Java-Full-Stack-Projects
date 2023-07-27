package com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.UserEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserEntity collectedData){
        LocalDateTime now = LocalDateTime.now();
        collectedData.setUser_createdAt(now);
        collectedData.setUser_updatedAt(now);
        userRepository.save(collectedData);
    }
}
