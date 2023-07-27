package com.zee.org.zee5_Clone.Repository;

import com.zee.org.zee5_Clone.Entity.LikeTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Likerepository extends JpaRepository<LikeTable,Integer> {
    LikeTable findByUseridAndVideoid(UserTable u, VideoTable v);
    LikeTable findByUseridUseridAndVideoidVideoid(int uid,int vid);
}
