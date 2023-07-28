package com.sivasai.endGame.SpringFinalProject.ResponseGenerator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<?> generateResponse(String message, HttpStatus status, Object responseObj){
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        if(responseObj != null){
            map.put("response", responseObj);
        }
        return new ResponseEntity<Object>(map, status);
    }
}

