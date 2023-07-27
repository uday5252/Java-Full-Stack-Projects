package com.abc.demo.Zee5.Apache.Kafka.Producer.CONTROLLER;

import com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class displayVideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/video/{video_id}/display")
    public String display(@PathVariable("video_id") int video_id, Model m){
        String videoUrl = videoService.getUrl(video_id);
        m.addAttribute("videoUrl", videoUrl);
        return "display_video";
    }
}
