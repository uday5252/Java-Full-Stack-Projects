package com.abc.demo.videostreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.videostreaming.Entity.LikeEntity;
import com.abc.demo.videostreaming.Entity.LikeId;
import com.abc.demo.videostreaming.EntityRepository.LikeInterfaceRepository;

@Service
public class LikeService {

    @Autowired
    LikeInterfaceRepository lir;

    @Autowired
    KafkaTemplate<String,String> kt;

    
    public void likeNotify(LikeEntity likeEntity) {
        kt.send("user_likes",likeEntity.toString());
    }


    public LikeEntity findById(LikeId id) {
        return lir.findById(id).get();
    }


    public void delete(LikeEntity findById) {
        lir.delete(findById);
    }


    public void save(LikeEntity likeEntity) {
        lir.save(likeEntity);
    }
}
