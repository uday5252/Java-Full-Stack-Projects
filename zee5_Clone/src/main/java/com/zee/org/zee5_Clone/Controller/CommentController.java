package com.zee.org.zee5_Clone.Controller;

import com.zee.org.zee5_Clone.Entity.CommentTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import com.zee.org.zee5_Clone.Kafka.KafkaProducer;
import com.zee.org.zee5_Clone.Repository.UserRepository;
import com.zee.org.zee5_Clone.Repository.VideoRepository;
import com.zee.org.zee5_Clone.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController
{
    @Autowired
    CommentService Cs;
    @Autowired
    KafkaProducer Kp;
    @Autowired
    VideoRepository Vr;
    @Autowired
    UserRepository Ur;

    @PostMapping("/add/commentto/{videoid}/{userid}")
    public ResponseEntity<String> createComment(@PathVariable("videoid") int vid,@PathVariable("userid") int uid,String commenttocreate)
    {

        UserTable u=Ur.findById(uid).get();
        CommentTable comment=Cs.AddCommentToVideo(vid,uid,commenttocreate);
        if(comment.getCommentid() != 0){
            Kp.sendCommentDataToTopic(comment.getCommentid());
            return new ResponseEntity<>("Comment created by "+u.getUsername(), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Comment already created by this user named "+u.getUsername(), HttpStatus.OK);
    }
}
