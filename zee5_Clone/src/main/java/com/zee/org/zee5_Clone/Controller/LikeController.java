package com.zee.org.zee5_Clone.Controller;

import com.zee.org.zee5_Clone.Entity.LikeTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Kafka.KafkaProducer;
import com.zee.org.zee5_Clone.Repository.UserRepository;
import com.zee.org.zee5_Clone.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {
    @Autowired
    LikeService Ls;
    @Autowired
    UserRepository Ur;
    @Autowired
    KafkaProducer Kp;

    @PostMapping("/like/add/{videoid}/{Userid}")
    public ResponseEntity<String> doLike(@PathVariable("videoid") int vid,@PathVariable("Userid") int uid){

        UserTable u=Ur.findById(uid).get();
        LikeTable l=Ls.createlike(vid,uid);
        if(l.getLikeid()!=0){
        Kp.sendLikeDataToTopic(l.getLikeid());
        return new ResponseEntity<>("liked by "+u.getUsername(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Already liked by "+u.getUsername(), HttpStatus.OK);
    }
}
