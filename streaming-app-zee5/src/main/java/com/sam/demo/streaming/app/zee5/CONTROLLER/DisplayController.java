package com.sam.demo.streaming.app.zee5.CONTROLLER;

import com.sam.demo.streaming.app.zee5.SERVICE.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DisplayController {

    @Autowired
    VideoService vs;
    @GetMapping("/video/{videoId}/display")
    public String display(@PathVariable("videoId") int vid, Model m){
        String videoUrl = vs.getUrl(vid);
        m.addAttribute("videoUrl",videoUrl);
        return "index.html";


    }

}
