package com.zee.Zee5Clone;

import com.zee.Zee5Clone.Entity.Video;
import com.zee.Zee5Clone.Repository.VideoRepository;
import com.zee.Zee5Clone.Service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class Zee5CloneApplicationTests {

	@Test
	void contextLoads() {
	}
//
//	@Autowired
//	private VideoService videoService;
//
//	@MockBean
//	private VideoRepository videoRepository;
//	@Test
//	public void uploadVideoTest(){
//		Video video = new Video("Video1","this is video 1","url",1,1,"time");
//		when(videoRepository.save(video)).thenReturn(video);

//	}

	@Autowired
	private VideoService videoService;
	@MockBean
	private VideoRepository videoRepository;
	@Test
	public void uploadVideoTest(){
		Video video = new Video("Video1","this is video 1","url",1,1,"time");
		Mockito.when(videoRepository.save(video)).thenReturn(video);
//        System.out.println(videoService.uploadVideo(video));
		assertEquals(video,videoService.uploadVideo(video));
	}

}
