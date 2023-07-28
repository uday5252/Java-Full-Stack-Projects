package com.abc.demo.videostreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.videostreaming.Entity.User;
import com.abc.demo.videostreaming.EntityRepository.UserInterfaceRepository;

@Service
public class UserService {
    @Autowired
    UserInterfaceRepository uir;

    public User finduser(String username) {
        return uir.findByUsername(username);
    }

    public void save(User user) {
        uir.save(user);
    }

    public void delete(User referenceById) {
        uir.delete(referenceById);
    }
}
