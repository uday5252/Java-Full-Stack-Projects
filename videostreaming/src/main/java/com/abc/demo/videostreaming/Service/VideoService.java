package com.abc.demo.videostreaming.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.abc.demo.videostreaming.Entity.Genre;
import com.abc.demo.videostreaming.Entity.User;
import com.abc.demo.videostreaming.Entity.Video;
import com.abc.demo.videostreaming.EntityRepository.GenreInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.UserInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.VideoInterfaceRepository;

@Service
public class VideoService {
    @Autowired
    VideoInterfaceRepository vir;

    @Autowired
    GenreInterfaceRepository gir;

    @Autowired
    UserInterfaceRepository uir;

    
    @Autowired
    KafkaTemplate<String,String> kt;
    
    public Genre findgenre(String genre) {
        return gir.findByName(genre);
    }

    public User finduser(String username) {
        return uir.findByUsername(username);
    }

    public void notifyUploads(Video v) {
        kt.send("video_uploads",v.toString());
    }

    public void save(Video v) {
        vir.save(v);
    }

    public void delete(Video referenceById) {
        vir.delete(referenceById);
    }

}
