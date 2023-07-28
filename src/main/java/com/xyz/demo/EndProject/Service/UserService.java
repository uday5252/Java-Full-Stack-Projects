package com.xyz.demo.EndProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.demo.EndProject.Entity.UserEntity;
import com.xyz.demo.EndProject.Interface.UserRepo;

@Service
public class UserService 
{
	@Autowired
    UserRepo userRepo;

	public void createUserData(UserEntity ue) 
	{
		userRepo.save(ue);		
	}

	public List<UserEntity> getAllUsers() {
		return userRepo.findAll();
	}

//	public UserEntity userListbyIdData(int id) 
//	{
//		UserEntity e= userRepo.findById(id).get();
//		return e;
//	}
	
	public void deleteUser(int id)
	{
		userRepo.delete(userRepo.findById(id).get());
	}
	public void updateUser(int id,UserEntity ue)
	{
		UserEntity user=userRepo.findById(id).get();
		user.setEmail(ue.getEmail()!=null ? ue.getEmail() : user.getEmail());
		user.setPassword(ue.getPassword()!=null ? ue.getPassword() : user.getPassword());
		user.setUserName(ue.getUserName()!=null ? ue.getUserName() : user.getUserName());
		userRepo.save(user);
	}
	
}
