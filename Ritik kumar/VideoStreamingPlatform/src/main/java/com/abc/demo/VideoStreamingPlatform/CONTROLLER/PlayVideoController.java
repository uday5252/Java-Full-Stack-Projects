package com.abc.demo.VideoStreamingPlatform.CONTROLLER;

import com.abc.demo.VideoStreamingPlatform.ENTITY.VideoEntity;
import com.abc.demo.VideoStreamingPlatform.SERVICE.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlayVideoController {

    @Autowired
    VideoService vs;

    @GetMapping("/api/play/{videoId}")
    public String playVideoById(@PathVariable("videoId") int vId, Model m)
    {
        String videoUrl=vs.getVideo(vId).getVideoUrl();
        m.addAttribute("videoUrl",videoUrl);
        return "playVideo";
    }
}
