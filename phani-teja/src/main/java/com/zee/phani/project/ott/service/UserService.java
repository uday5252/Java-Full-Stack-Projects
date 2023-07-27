package com.zee.phani.project.ott.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.phani.project.ott.dao.UserDao;
import com.zee.phani.project.ott.dto.UserRequestDto;
import com.zee.phani.project.ott.dto.UserResponseDto;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void saveUserData(UserRequestDto inDto) throws IllegalArgumentException {
        if (Objects.isNull(inDto))
            throw new IllegalArgumentException("The inDto object cant be null");
        if (Objects.isNull(inDto.getEmail()) || Objects.isNull(inDto.getUserName())
                || Objects.isNull(inDto.getPassword()))
            throw new IllegalArgumentException("The required fields userName, email & password cant be null");

        // RETURNS an UserResponseDto
        userDao.addUser(inDto);

    }

    public void deleteUser(int userId) throws IllegalArgumentException, NoSuchElementException {
        if (Objects.isNull(userId))
            throw new IllegalArgumentException("userID cannot be null");
        userDao.deleteUser(userId);
    }

    public UserResponseDto getUser(int userId) {
        return userDao.getUser(userId);
    }
}
