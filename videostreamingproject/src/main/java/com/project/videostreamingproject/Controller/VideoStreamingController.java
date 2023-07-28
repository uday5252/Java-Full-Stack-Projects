package com.project.videostreamingproject.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.project.videostreamingproject.entity.Genre;
import com.project.videostreamingproject.entity.User;
import com.project.videostreamingproject.entity.VideoTabel;
import com.project.videostreamingproject.helpfulclasses.Bolo;
import com.project.videostreamingproject.service.GenreService;
import com.project.videostreamingproject.service.UserService;
import com.project.videostreamingproject.service.VideoService;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;
@Controller
public class VideoStreamingController {
	
	@Autowired
	VideoService Vs;
	@Autowired
	UserService Us;
	@Autowired
	GenreService Gs;
	
	
	@GetMapping("/play/{videoid}")
    public String playVideo(@PathVariable ("videoid") int id, Model model) {
		
		VideoTabel vt=Vs.find(id);
		String url=vt.getUrl();
        model.addAttribute("videoUrl", url);
        return "video";}
    
	@GetMapping("/newuser!")
	public String newuser( Model model)
	{
		User u=new User();
		model.addAttribute("user",u);
		
		return "newuser";
	}
	
	
	@PostMapping("/submit")
	public String neww(User u,Model m)
	{   
		System.out.println(u);
		Us.newuser(u);
		String s=u.getUserName();
	
		return "redirect: /homepage";
	}
	
	@GetMapping("/homepage")
    public String homePage(Model model ){
        List<Genre> genres = Gs.findallgenres(); 
       
        model.addAttribute("genres", genres);
        return "homepage";
    }
	
	

	
	@PostMapping("/genre-details")
	public String getvideo( String selectedGenre,Model m)
	{   
		List<VideoTabel> vt=Vs.findallofgenrebyname(selectedGenre);
		m.addAttribute("vt",vt);
		return "videopage";
	}
	

	@PostMapping("/Video-details")
    public String playvideo(Model model,String selectedvideo) { 
		VideoTabel vt=Vs.getvideobyname(selectedvideo);
		String url=vt.getUrl();
        model.addAttribute("url", url);
        return "videorunpage";
    }
	
	@GetMapping("/upload-video")
	public String newvideoupload(Model m)
	{
		VideoTabel vt=new VideoTabel();
		m.addAttribute("vt", vt);
		return "newvideoupload";
	}
}
