package com.videostreamingapp.com.Assignment.project.CONTROLLER;

import com.videostreamingapp.com.Assignment.project.ENTITY.VideoEntity;
import com.videostreamingapp.com.Assignment.project.SERVICE.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DisplayVideoController {
    @Autowired
    VideoService vs;
    @GetMapping("/video/{video_id}/display")
    public String display(@PathVariable int video_id, Model m){
        String videourl = vs.get_url(video_id);
        m.addAttribute("videourl",videourl);
        return "display_video";


    }


}
