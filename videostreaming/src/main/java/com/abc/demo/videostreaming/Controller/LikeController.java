package com.abc.demo.videostreaming.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    void gotLiked(@RequestBody LikeDao commentDao){
        User user = uir.getReferenceById(commentDao.getUserid());
        Video video  = vir.getReferenceById(commentDao.getVideoid());
        lir.save(new LikeEntity(user,video));
        ls.likeNotify(new LikeEntity(user,video));
    }

    @Operation(
        description = "Delete Like",
        summary = "Delete Like"
    )
    @DeleteMapping("/unlike")
    void unLike(@RequestBody LikeDao commentDao){
        User user = uir.getReferenceById(commentDao.getUserid());
        Video video  = vir.getReferenceById(commentDao.getVideoid());
        LikeId id = new LikeId(user,video);
        lir.delete(lir.findById(id).get());
    }
}
