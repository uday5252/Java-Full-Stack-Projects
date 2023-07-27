package com.zee.org.zee5_Clone.Service;


import com.zee.org.zee5_Clone.Entity.UserTable;

import com.zee.org.zee5_Clone.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService
{
    @Autowired
    UserRepository Ur;

    public List<UserTable> ViewAllUsers(){
        return Ur.findAll();
    }

    public void createUser(UserTable user){
        Ur.save(user);
    }

    public UserTable updateUser(int a,UserTable user){
        UserTable data=Ur.findById(a).get();
        data.setUsername(user.getUsername());
        data.setEmail(user.getEmail());
        data.setPassword(user.getPassword());
        return Ur.save(data);
    }

    public void DeleteUser(int a){
        Ur.deleteById(a);
    }

}
