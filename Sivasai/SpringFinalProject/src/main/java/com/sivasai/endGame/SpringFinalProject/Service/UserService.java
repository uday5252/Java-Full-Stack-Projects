package com.sivasai.endGame.SpringFinalProject.Service;

import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User getUser(String userName, String password, String email){
        Boolean bool = userRepository.existsByUserName(userName);
        if(bool) {
            User user = userRepository.findByUserName(userName).get();
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
//                System.out.println("returned user");
                return user;
            }
            return new User("error");
        }
        else {
            return null;
        }
    }

    public User getUserByName(String name){
        if(userRepository.existsByUserName(name)){
            return userRepository.findByUserName(name).get();
        }
        else {
            return null;
        }
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public Boolean existsById(int userId){
        return userRepository.existsById(userId);
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).get();
    }
}
