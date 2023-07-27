package com.abc.demo.Zee5.OTT.Project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.Zee5.OTT.Project.Entity.User;
import com.abc.demo.Zee5.OTT.Project.Entity.Video;
import com.abc.demo.Zee5.OTT.Project.Entity.likes;


public interface LikeRepository extends JpaRepository<likes, Integer> {

	likes findByUserAndVideo(User user, Video video);

}
