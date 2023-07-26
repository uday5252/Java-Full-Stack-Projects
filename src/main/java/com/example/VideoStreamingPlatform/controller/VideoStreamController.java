package com.example.VideoStreamingPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.VideoStreamingPlatform.service.VideoService;

@Controller
@RequestMapping("/api")
public class VideoStreamController {
  @Autowired
  VideoService vs;

  @GetMapping("/videos/{video_id}/stream")
  public String playVideo(@PathVariable int video_id, Model m) {
    String videoUrl = vs.getVideoUrl(video_id);
    m.addAttribute("videoUrl", videoUrl);
    return "play_video";
  }
}
