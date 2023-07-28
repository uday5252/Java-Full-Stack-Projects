package com.example.endtoendproject.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.endtoendproject.ENTITY.Comments;
import com.example.endtoendproject.PRODUCER.KafkaProducer;
import com.example.endtoendproject.SERVICE.CommentService;

@RestController
public class CommentController {
	@Autowired
	CommentService cs;
	@Autowired
	KafkaProducer kp;
	@PostMapping("/{videoId}/comment")
	public ResponseEntity<String> insertComment(@RequestBody Comments comment, @PathVariable("videoId") int vid){
		cs.createComent(comment, vid);
		kp.sendData2("comment for vide recorded");
		return new ResponseEntity<>("commented" ,HttpStatus.CREATED);
		}
}
