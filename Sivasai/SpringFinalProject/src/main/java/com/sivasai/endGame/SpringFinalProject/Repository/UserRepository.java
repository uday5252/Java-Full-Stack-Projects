package com.sivasai.endGame.SpringFinalProject.Repository;

import com.sivasai.endGame.SpringFinalProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
}
