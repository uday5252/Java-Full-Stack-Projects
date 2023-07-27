package com.zee.org.zee5_Clone.Controller;

import com.zee.org.zee5_Clone.Entity.VideoTable;
import com.zee.org.zee5_Clone.Repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VideoStreaming
{
    @Autowired
    VideoRepository Vr;

    @GetMapping("/stream/video/{videoId}")
    public String playVideo(@PathVariable("videoId")int a, Model m){
        VideoTable v=Vr.findById(a).get();
        String Url=v.getUrl();
        m.addAttribute("data",Url);
        return "streamview";
    }

}
