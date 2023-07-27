package com.zee.phani.project.ott.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.phani.project.ott.dao.CommentDao;
import com.zee.phani.project.ott.dto.CommentDto;
import com.zee.phani.project.ott.kafka.KafkaProducer;

@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    KafkaProducer kp;

    String topic = "user_comments";

    public void createComment(CommentDto inDto) throws Exception {
        try {
            CommentDto cDto = commentDao.createComment(inDto);
            kp.UploadToTopic(topic, cDto.getComment());
        } catch (NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw e;
            // throw new IllegalArgumentException("The Comment already exists in the
            // Application cannot Comment again");
        }
    }

    public List<CommentDto> getAllComments() {
        return commentDao.getAllComments();
    }

    public void deleteComment(CommentDto inDto) {
        commentDao.deleteComment(inDto);
    }
}
