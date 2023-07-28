package com.example.demo.End.project.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.End.project.ENTITY.VideoEntity;
import com.example.demo.End.project.REPOSITORY.VideoRepositoryInterface;
import com.example.demo.End.project.SERVICE.VideoService;

@Controller
public class VideoStreamingController {
     @Autowired
     VideoRepositoryInterface vri;
     
     @Autowired
     VideoService vs;
     @GetMapping("/stream/video/{videoid}")
     public String streaming(@PathVariable("videoid") int id, Model m)
     {
    	 VideoEntity ve = vri.findById(id).get();
    	 String Url=ve.getUrl();
    	    m.addAttribute("data",Url);
    	 return "Streaming";
     }
     
     @GetMapping("/stream/allVideo")
     public String AllvideoStreaming(Model m)
     {
    	 List<VideoEntity> ve = vs.getAllVideo();
    	 m.addAttribute("list", ve);
    	 return "AllVideos";
     }
     @GetMapping("/api/genre/{genreid}/videos")
 	public String GetGenreVideos(@PathVariable("genreid") int id, Model m)
 	{
 		List<VideoEntity> ve = vs.getgenreVideos(id);
 		m.addAttribute("list", ve);
 		return "GenreVideos";
 	}
     
}
