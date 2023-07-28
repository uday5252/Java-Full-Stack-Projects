package com.example.endtoendproject.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.endtoendproject.ENTITY.Likes;
import com.example.endtoendproject.PRODUCER.KafkaProducer;
import com.example.endtoendproject.SERVICE.LikeService;

@RestController
public class LikeController {
@Autowired
LikeService ls;
@Autowired
KafkaProducer kp;
@PostMapping("/{videoId}/like")
public ResponseEntity<String> insertLike(@RequestBody Likes like, @PathVariable("videoId") int vid){
	ls.createLike(like, vid);
	kp.sendData1("video has been liked");
	return new ResponseEntity<>("post was liked" ,HttpStatus.CREATED);
}
}
