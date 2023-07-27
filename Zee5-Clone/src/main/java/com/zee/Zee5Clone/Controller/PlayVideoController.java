package com.zee.Zee5Clone.Controller;

import com.zee.Zee5Clone.Entity.Video;
import com.zee.Zee5Clone.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayVideoController {
    @Autowired
    VideoService videoService;
    @GetMapping("/play/{videoId}")
    public String playVideo(@PathVariable("videoId") int videoId, Model m){
        Video video =videoService.getVideo(videoId);
        String videoUrl = video.getUrl();
        m.addAttribute("url",videoUrl);
        return "playVideo";
    }
}
