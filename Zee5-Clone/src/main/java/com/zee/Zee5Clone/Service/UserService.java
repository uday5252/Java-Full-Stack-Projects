package com.zee.Zee5Clone.Service;

import com.zee.Zee5Clone.Entity.User;
import com.zee.Zee5Clone.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String time = dtf.format(now);
    public void saveUser(User user){
        user.setCreatedAt(time);
        user.setUpdatedAt(time);
        userRepository.save(user);
    }
}
