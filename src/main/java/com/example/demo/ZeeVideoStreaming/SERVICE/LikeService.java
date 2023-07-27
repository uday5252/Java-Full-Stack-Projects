package com.example.demo.ZeeVideoStreaming.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.ZeeVideoStreaming.ENTITY.LikeEntity;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.LikeRepositoryInterface;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.VideoRepositoryInterface;

@Service
public class LikeService {

	@Autowired
	LikeRepositoryInterface lri;

	@Autowired
	VideoRepositoryInterface vri;

	@Autowired
	KafkaTemplate<Integer, String> kt;

	public void createLikeInDB(LikeEntity like) {
		lri.save(like);
		
		String userName = like.getUser().getUserName();
		
		String videoName = like.getVideo().getTitle();

		String data = String.format("User %1$s liked %2$s.", userName, videoName);
		kt.send("user_likes",data);
	}

	public LikeEntity getLikeByUserAndVideoFromDB(long uid, long vid) {
		return lri.findByUserIdAndVideoId(uid, vid);

	}

	public boolean deleteLikeByUserAndVideoFromDB(long uid, long vid) {
		LikeEntity like = lri.findByUserIdAndVideoId(uid, vid);
		if (like != null) {
			String userName = like.getUser().getUserName();
			
			String videoName = like.getVideo().getTitle();

			String data = String.format("User %1$s unliked %2$s.", userName, videoName);
			kt.send("user_likes",data);
			lri.delete(like);
			return true;
		}
		return false;
	}

	public List<LikeEntity> getAllLikesFromDB() {
		return lri.findAll();
	}
	
	public List<LikeEntity> getAllLikesByVideoIDFromDB(long vid) {
		return lri.findByVideoId(vid);
	}
}
