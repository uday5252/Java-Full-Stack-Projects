package com.abc.project1.service;

import com.abc.project1.ExceptionHandling.CustomException.InvalidIdException;
import com.abc.project1.ExceptionHandling.CustomException.ResourceNotFoundException;
import com.abc.project1.entity.User;
import com.abc.project1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo ur;
    public User addThisUser(User user) {
        return ur.save(user);
    }

    public User getThisByUserId(int uploadedById) throws InvalidIdException {
        if(uploadedById <= 0){
            throw new InvalidIdException(uploadedById);
        }
        return ur.findByUid(uploadedById);
    }

    public List<User> getAllUsers() {
        return ur.findAll();
    }

    public void deleteThisUserById(int userId) throws InvalidIdException, ResourceNotFoundException {
        if(userId <= 0){
            throw new InvalidIdException(userId);
        }
        User user = ur.findByUid(userId);
        if(user == null){
            throw new ResourceNotFoundException(userId);
        }
        ur.delete(user);
    }
}
