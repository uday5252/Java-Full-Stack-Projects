package com.abc.demo.VideoStreamingPlatform.SERVICE;

import com.abc.demo.VideoStreamingPlatform.ENTITY.UserEntity;
import com.abc.demo.VideoStreamingPlatform.REPOSITORY.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepositoryInterface uri;

    public UserEntity createUser(UserEntity user)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        user.setUserCreatedAt(date);
        uri.save(user);
        return user;
    }

    public List<UserEntity> showAllUser()
    {
        List<UserEntity> l=uri.findAll();
        return l;
    }

}
