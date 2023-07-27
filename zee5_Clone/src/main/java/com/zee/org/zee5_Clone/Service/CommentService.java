package com.zee.org.zee5_Clone.Service;

import com.zee.org.zee5_Clone.Entity.CommentTable;
import com.zee.org.zee5_Clone.Entity.UserTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import com.zee.org.zee5_Clone.Repository.CommentRepository;
import com.zee.org.zee5_Clone.Repository.UserRepository;
import com.zee.org.zee5_Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService
{
    @Autowired
    CommentRepository Cr;
    @Autowired
    VideoRepository Vr;
    @Autowired
    UserRepository Ur;


    public CommentTable AddCommentToVideo(int vid,int uid,String comment){
        VideoTable v=Vr.findById(vid).get();
        UserTable u=Ur.findById(uid).get();
        CommentTable c=new CommentTable();


        if(Cr.findByUserAndVideo(u,v) == null){
            System.out.println("_________________________________");
            CommentTable com=new CommentTable();
            com.setComment(comment);
            com.setVideo(v);
            com.setUser(u);
            CommentTable c1=Cr.save(com);

            c=c1;
        }
        return c;

    }

}
