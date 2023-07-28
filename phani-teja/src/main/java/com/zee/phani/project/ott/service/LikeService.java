package com.zee.phani.project.ott.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.phani.project.ott.dao.LikeDao;
import com.zee.phani.project.ott.dto.LikeDto;
import com.zee.phani.project.ott.kafka.KafkaProducer;

@Service
public class LikeService {

    @Autowired
    LikeDao likeDao;

    @Autowired
    KafkaProducer kp;

    String topic = "user_likes";

    public void createLike(LikeDto inDto) throws Exception {
        try {
            LikeDto lDto = likeDao.createLike(inDto);
            kp.UploadToTopic(topic, lDto.getLike());
        } catch (NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw e;
            // throw new IllegalArgumentException("The Like already exists in the
            // Application cannot like again");
        }
    }

    public List<LikeDto> getAllLikes() {
        return likeDao.getAllLikes();
    }

    public void deleteLike(LikeDto inDto) {
        likeDao.deleteLike(inDto);
    }
}
