package com.abc.demo.Final.Project.implementing.all.SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.demo.Final.Project.implementing.all.Entity.UserEntity;
import com.abc.demo.Final.Project.implementing.all.REPOSITORY.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	public void userAdd(UserEntity ue) {
		ur.save(ue);
	}
}
