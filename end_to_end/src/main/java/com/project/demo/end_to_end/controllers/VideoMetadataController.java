package com.project.demo.end_to_end.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.demo.end_to_end.KafkaProducer;
import com.project.demo.end_to_end.entities.Genre;
import com.project.demo.end_to_end.entities.User;
import com.project.demo.end_to_end.entities.Video;
import com.project.demo.end_to_end.service.CommentService;
import com.project.demo.end_to_end.service.GenreService;
import com.project.demo.end_to_end.service.LikeService;
import com.project.demo.end_to_end.service.UserService;
import com.project.demo.end_to_end.service.VideoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;
@Tag(name = "Video Controller", description = "This has endpoints to manipulate with videos")
@Controller
public class VideoMetadataController {
    String user,pass;
    @Autowired
    VideoService vs;
    @Autowired
    UserService us;
    @Autowired
    GenreService gs;
    @Autowired
    ResourceLoader rl;
    @Autowired
    LikeService ls;
    @Autowired
    CommentService cs;
    @Autowired
    KafkaProducer kp;
    /* Home page:- http://lcoalhost:8080/ */
    @GetMapping("/iframe/{videoId}")
    public String iframe(@PathVariable("videoId") int videoId,Model m){
        m.addAttribute("videoId",videoId);
        m.addAttribute("user", user);
        m.addAttribute("likecount",ls.findByVideo(videoId).size());
        return "like";
    }
    @GetMapping("/iframe/comment/{videoId}")
    public String iframes(@PathVariable("videoId") int videoId,Model m){
        m.addAttribute("videoId",videoId);
        m.addAttribute("user", user); 
        m.addAttribute("comments",cs.getAllCommets(videoId)); 
        return "comment";
    }
   

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/user_registration")
    public String registration(Model m){
        User u = new User();
        m.addAttribute("u",u);
        return "user_registration";
    }
    @PostMapping("/registering_user")
    public String registering(@ModelAttribute(value = "u") User user)
    {
        us.addUser(user);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){
        if(user == null || pass == null) return "login";
        return "login";
    }
    @PostMapping("/loggingin")
    public String logging(@RequestParam("user") String username, @RequestParam("pass") String password){
        user = username; pass = password;
        User u = us.findbycredentials(username,password);
        if(u == null) return "redirect:/login";
        return "loginhome";
    }
    @GetMapping("/loginhome")
    public String loginhome(){
        if(user == null) return "redirect:/login";
        User u = us.findbycredentials(user,pass);
        if(u == null) return "redirect:/login";
        return "loginhome";
    }
    @GetMapping("/logout")
    public String LogOut(){
        user = null; pass = null;
        return "redirect:/";
    }
    @GetMapping("/api/videoslist")
    public String listVideos(Model m){
        List<Video> lsv = vs.getAllVideos();
        m.addAttribute("lsv", lsv);
        return "videolist";
    }
    @GetMapping("/playvideo/{videoId}")
    public String playVideo(@PathVariable("videoId") int videoId,Model m){
        m.addAttribute("videoId",videoId);
        return "playvideo";
    }
    @GetMapping("/api/genres/{genreId}/videolist")
    public String listVideos(@PathVariable("genreId") int genreId,Model m){
        List<Video> lsv = vs.findByGenreId(genreId);
        m.addAttribute("lsv",lsv);
        return "videolist";
    }
    @GetMapping("/api/genreslist")
    public String displayGenresList(Model m){
        List<Genre> lsv = gs.getAllGenres();
        m.addAttribute("lsv", lsv);
        return "genreslist";
    }
    @ResponseBody
    @PostMapping("/api/admin/videos")
    public ResponseEntity<?> postVideo(@RequestBody Video v, String username, String passwrod,String ge){
        User user = us.findbycredentials(username, passwrod);
        if(user == null)    return new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        v.setUploadedBy(user);
        Genre g = gs.getByName(ge);
        if(g == null)   return new ResponseEntity<String>("invalidGenres",HttpStatus.NOT_ACCEPTABLE);
        v.setGenre(g);
        vs.addVideo(v);
        kp.send("video_uploads", "user "+username+" uploaded a video with details:- "+v.toString());
        return new ResponseEntity<Video>(v, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "/api/videos/{videoId}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable("videoId") int videoId){
        return Mono.fromSupplier(()->{
            return rl.getResource("file:"+vs.getVideo(videoId).getUrl());
        });
    }
    @ResponseBody
    @GetMapping("/api/videos")
    public String getVideos(){
        return new ResponseEntity<>(vs.getAllVideos(),HttpStatus.OK).toString();
    }
    @ResponseBody
    @GetMapping("/api/genres/{genreId}/videos")
    public ResponseEntity<List<Video>> getGenreVideos(@PathVariable("genreId") int genreId){
        return new ResponseEntity<>(vs.findByGenreId(genreId),HttpStatus.OK);
    }
    @ResponseBody
    @PutMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<?> updateVideo(@PathVariable("videoId") int videoId, @RequestBody Video v,String username, String password){
        User user = us.findbycredentials(username, password);
        if(user == null) new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        Video vold = vs.getVideo(videoId);
        if(v.getTitle() != null)    vold.setTitle(v.getTitle());
        if(v.getDescription() != null)  vold.setDescription(v.getDescription());
        if(v.getUrl() != null) vold.setUrl(v.getUrl());
        vs.addVideo(vold);
        return new ResponseEntity<Video>(vold, HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> deleteVideo(@PathVariable("videoId") int videoId,String username, String password){
        User user = us.findbycredentials(username, password);
        if(user == null) new ResponseEntity<String>("invalidCredentials",HttpStatus.NOT_ACCEPTABLE);
        vs.deleteVideo(videoId);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
