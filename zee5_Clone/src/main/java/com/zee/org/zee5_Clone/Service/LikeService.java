package com.zee.org.zee5_Clone.Service;

import com.zee.org.zee5_Clone.Entity.LikeTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import com.zee.org.zee5_Clone.Repository.Likerepository;
import com.zee.org.zee5_Clone.Repository.UserRepository;
import com.zee.org.zee5_Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    Likerepository Lr;
    @Autowired
    VideoRepository Vr;
    @Autowired
    UserRepository Ur;

//    int likeCount=0;

    public LikeTable createlike(int vid,int uid){
        VideoTable v=Vr.findById(vid).get();
        UserTable u=Ur.findById(uid).get();
        LikeTable li=new LikeTable();


        if(Lr.findByUseridAndVideoid(u,v)==null){
//            System.out.println("_________________________________");
            LikeTable like=new LikeTable();
            like.setUserid(u);
            like.setVideoid(v);
            int count =v.getLikes();
            count++;
            v.setLikes(count);
            LikeTable l=Lr.save(like);
            li=l;
        }
        return li;
    }
}
