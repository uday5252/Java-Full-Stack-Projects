package com.zee.Zee5Clone;

import com.zee.Zee5Clone.Entity.Video;
import com.zee.Zee5Clone.Repository.VideoRepository;
import com.zee.Zee5Clone.Service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
public class VideoServiceTest {
    @Autowired
    private VideoService videoService;
    @MockBean
    private VideoRepository videoRepository;
    @Test
    public void uploadVideoTest(){
        Video video = new Video("Video1","this is video 1","url",1,1,"10/11/12");
        Mockito.when(videoRepository.save(video)).thenReturn(video);
//        System.out.println(videoService.uploadVideo(video));
        assertEquals(video,videoService.uploadVideo(video));
    }

//    @Test
//    public void getVideoTest(){
//        int videoId = 1;
//        Video video = new Video("Video1","this is video 1","url",1,1,"10/11/12");
//        video.setId(1);
//        videoRepository.save(video);
//        System.out.println(video);
//        Mockito.when(videoRepository.findById(videoId).get()).thenReturn(video);
//        assertEquals(video,videoService.getVideo(videoId));
//    }





}
