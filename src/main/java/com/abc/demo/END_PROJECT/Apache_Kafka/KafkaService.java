package com.abc.demo.END_PROJECT.Apache_Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

	
	@Autowired
	KafkaTemplate<Integer, String> kt;
	public void sendDataToVideo_UploadsTopic(String message) {
		kt.send("video_uploads",message);
	}
	
	public void sendDataToUser_LikesTopic(String message) {
		kt.send("user_likes",message);
	}
	
	public void sendDataToGenre_UpdatesTopic(String message) {
		kt.send("genre_updates",message);
	}
	
	public void sendDataToUser_CommentsTopic(String message) {
		kt.send("user_comments",message);
	}
	
}
