package com.example.demo.ZeeVideoStreaming.CONTROLLER;
 
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj,
			boolean value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);
		map.put("success", value);
		map.put("timestamp", java.time.LocalDateTime.now());

		return new ResponseEntity<Object>(map, status);
	}
}
