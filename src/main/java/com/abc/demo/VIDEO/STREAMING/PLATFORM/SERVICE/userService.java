package com.abc.demo.VIDEO.STREAMING.PLATFORM.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.abc.demo.VIDEO.STREAMING.PLATFORM.ENTITY.userEntity;
import com.abc.demo.VIDEO.STREAMING.PLATFORM.REPOSITORY.userRepository;

@Service
public class userService {
	
	@Autowired
	userRepository ur;

	public void register(userEntity ue) {
		
		ur.save(ue);	
		
	}

	public void delete(int id) {
		
		ur.deleteById(id);
		
	}

	public void update(int id, userEntity updatedData) {
		
		userEntity user =ur.findById(id).get();
		
			
			user.setUsername(updatedData.getUsername() != null ? updatedData.getUsername():user.getUsername());
			
			user.setEmail(updatedData.getEmail() != null ? updatedData.getEmail():user.getEmail());
			
			user.setPassword(updatedData.getPassword() != null ? updatedData.getPassword():user.getPassword());
			
			
			ur.save(user);
			
	}

	public userEntity getUserById(int id) {
		
		userEntity user=ur.findById(id).orElse(null);
		
	 return user;	
		
	}
	
	

}
