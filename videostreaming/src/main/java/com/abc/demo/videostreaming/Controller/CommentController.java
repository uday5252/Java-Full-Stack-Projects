package com.abc.demo.videostreaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.videostreaming.Entity.CommentEntity;
import com.abc.demo.videostreaming.Entity.LikeId;
import com.abc.demo.videostreaming.Entity.User;
import com.abc.demo.videostreaming.Entity.Video;
import com.abc.demo.videostreaming.EntityRepository.CommentInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.UserInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.VideoInterfaceRepository;
import com.abc.demo.videostreaming.Service.CommentService;
import com.abc.demo.videostreaming.doa.CommentDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
    name = "Comments Controller",
    description = "Comments Controller"
)
@RestController
public class CommentController {

    @Autowired
    CommentInterfaceRepository cir;

    @Autowired
    UserInterfaceRepository uir;

    @Autowired
    VideoInterfaceRepository vir;

    @Autowired
    CommentService cs;
    
    @Operation(
        description = "Add Comment",
        summary = "Add Comment"
    )
    @PostMapping("/comment")
    ResponseEntity<String> gotCommented(@RequestBody CommentDao commentDao){
        User user = uir.getReferenceById(commentDao.getUserid());
        Video video  = vir.getReferenceById(commentDao.getVideoid());
        try {
            cir.save(new CommentEntity(user,video,commentDao.getDescription()));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        cs.commentNotify(new CommentEntity(user,video,commentDao.getDescription()));
        return new ResponseEntity<String>("Commented", HttpStatus.OK);
    }

    @Operation(
        description = "Delete Comment",
        summary = "Delete Comment"
    )
    @DeleteMapping("/uncomment")
    ResponseEntity<String> unComment(@RequestBody CommentDao commentDao){
        User user = uir.getReferenceById(commentDao.getUserid());
        Video video  = vir.getReferenceById(commentDao.getVideoid());
        LikeId id = new LikeId(user,video);
        try {
            cir.delete(cir.findById(id).get());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Uncommented", HttpStatus.OK);
    }
}
