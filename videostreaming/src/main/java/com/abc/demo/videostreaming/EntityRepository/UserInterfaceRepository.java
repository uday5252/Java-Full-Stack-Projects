package com.abc.demo.videostreaming.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.videostreaming.Entity.User;


public interface UserInterfaceRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
