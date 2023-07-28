package com.zee.phani.project.ott.dao;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zee.phani.project.ott.dto.UserRequestDto;
import com.zee.phani.project.ott.dto.UserResponseDto;
import com.zee.phani.project.ott.entity.UserEntity;
import com.zee.phani.project.ott.repository.UserRepository;

@Component
public class UserDao {

    @Autowired
    UserRepository repo;

    @Autowired
    ModelMapper mapper;

    public UserResponseDto addUser(UserRequestDto inDto) {
        UserEntity user = mapper.map(inDto, UserEntity.class);
        user = repo.save(user);
        UserResponseDto userDto = mapper.map(user, UserResponseDto.class);
        userDto.setUser(user);
        return userDto;
        // System.out.println(repo.save(user));

    }

    public void deleteUser(int userId) throws NoSuchElementException {
        UserEntity user = repo.findById(userId).get();
        repo.delete(user);
    }

    public UserResponseDto getUser(int userId) throws NoSuchElementException {
        UserEntity userEntity = repo.findById(userId).get();
        UserResponseDto userDto = mapper.map(userEntity, UserResponseDto.class);
        userDto.setUser(userEntity);
        return userDto;
    }
}
