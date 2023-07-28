package com.abc.demo.videostreaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.videostreaming.Entity.LikeEntity;
import com.abc.demo.videostreaming.Entity.LikeId;
import com.abc.demo.videostreaming.Entity.User;
import com.abc.demo.videostreaming.Entity.Video;
import com.abc.demo.videostreaming.EntityRepository.LikeInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.UserInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.VideoInterfaceRepository;
import com.abc.demo.videostreaming.Service.LikeService;
import com.abc.demo.videostreaming.doa.LikeDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
    name = "Like Controller",
    description = "Like Controller"
)
@RestController
public class LikeController {
    @Autowired
    LikeInterfaceRepository lir;

    @Autowired
    UserInterfaceRepository uir;

    @Autowired
    VideoInterfaceRepository vir;

    @Autowired
    LikeService ls;
    
    @Operation(
        description = "Add like",
        summary =  "Add like"
    )
    @PostMapping("/like")
    ResponseEntity<String> gotLiked(@RequestBody LikeDao commentDao){
        User user = uir.getReferenceById(commentDao.getUserid());
        Video video  = vir.getReferenceById(commentDao.getVideoid());
        try {
            ls.save(new LikeEntity(user,video));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        ls.likeNotify(new LikeEntity(user,video));
        return new ResponseEntity<String>("Liked", HttpStatus.OK);
    }

    @Operation(
        description = "Delete Like",
        summary = "Delete Like"
    )
    @DeleteMapping("/unlike")
    ResponseEntity<String> unLike(@RequestBody LikeDao commentDao){
        User user = uir.getReferenceById(commentDao.getUserid());
        Video video  = vir.getReferenceById(commentDao.getVideoid());
        LikeId id = new LikeId(user,video);
        try {
            ls.delete(ls.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<String>("Unliked", HttpStatus.OK);
    }
}
