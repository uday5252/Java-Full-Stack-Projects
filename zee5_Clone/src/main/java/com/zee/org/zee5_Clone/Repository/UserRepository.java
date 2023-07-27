package com.zee.org.zee5_Clone.Repository;

import com.zee.org.zee5_Clone.Entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserTable,Integer> {
}
