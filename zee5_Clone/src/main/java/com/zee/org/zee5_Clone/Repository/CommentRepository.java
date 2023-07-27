package com.zee.org.zee5_Clone.Repository;

import com.zee.org.zee5_Clone.Entity.CommentTable;
import com.zee.org.zee5_Clone.Entity.LikeTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentTable,Integer> {
    CommentTable findByUserAndVideo(UserTable u, VideoTable v);
//    LikeTable findByUseridAndVideoid(UserTable u, VideoTable v);
}
