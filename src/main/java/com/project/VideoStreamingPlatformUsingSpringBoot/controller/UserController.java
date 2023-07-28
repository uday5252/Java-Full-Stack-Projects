package com.project.VideoStreamingPlatformUsingSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.VideoStreamingPlatformUsingSpringBoot.entity.commentsEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.genresEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.likesEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.ratingEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.usersEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.entity.videosEntity;
import com.project.VideoStreamingPlatformUsingSpringBoot.kafka.KafkaService;
import com.project.VideoStreamingPlatformUsingSpringBoot.service.GenreService;
import com.project.VideoStreamingPlatformUsingSpringBoot.service.UserService;
import com.project.VideoStreamingPlatformUsingSpringBoot.service.VideoService;

@Controller
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserService us;
	@Autowired
	VideoService vs;
	@Autowired
	GenreService gs;
	@Autowired
	KafkaService ks;
	// Home page
	@GetMapping("/")
	public String homepage() {
		return "homepage";
	}
	// Displays user form for user creation
	@GetMapping("/user/create")
	public String createUser(usersEntity ue, Model m){
		m.addAttribute("u1",ue);
		return "userform";
	}
	// Collects user data and stores
	@PostMapping("/user/collect")
	public String collectUser(usersEntity ue) {
		us.createuser(ue);
		return "homepage";
	}
	//Display list of genres
	@GetMapping("/genres/list")
	public String listGenres(Model m){
		List<genresEntity> ls = gs.listgenres();
		m.addAttribute("g", ls);
		return "listgenres";
	}
	// Display videos of specified genre
	@GetMapping("/genres/{genreid}/videos/list")
	public String listVideosByGenre(@PathVariable("genreid") int gid, Model m){
		List<videosEntity> ls = vs.listvideosByGenre(gid);
		m.addAttribute("glist", ls);
		return "listGenreVideos";
	}
	
//	@GetMapping("/videos/{videoId}/get")
//	public String getVideo(@PathVariable("videoId") int vid, Model m) {
//		m.addAttribute("v", vs.getvideo(vid));
//		return "viewVideo";
//	}
	
	//Displays all videos
	@GetMapping("/videos/list")
	public String listVideos(Model m){
		List<videosEntity> ls = vs.listvideos();
		m.addAttribute("vlist",ls);
		return "listVideos";
	}
	//Add Like to video
	@GetMapping("/like/create/{id}")
	public String likeVideo(@PathVariable("id") int vid,likesEntity le, Model m){
		m.addAttribute("l",le);
		m.addAttribute("id1",vid);
		return "createlike";
	}
	//Collects like info
	@PostMapping("/like/collect/{id}")
	public String collectLike(@PathVariable("id") int vid, likesEntity le, Model m){
		m.addAttribute("str", us.likevideo(le,vid));
		ks.sendDataToUser_LikesTopic(le.getUser().getUserId()+" liked the video");
		ks.sendDataToUser_LikesTopic(le.toString());
		return "displaylike";
	}
	//Add comment to the video
	@GetMapping("/comment/create/{id}")
	public String commentVideo(@PathVariable("id") int vid,commentsEntity ce, Model m){
		m.addAttribute("c",ce);
		m.addAttribute("id2",vid);
		return "createcomment";
	}
	//Collects comment info
	@PostMapping("/comment/collect/{id}")
	public String collectComment(@PathVariable("id") int vid, commentsEntity ce, Model m){
		m.addAttribute("str1", us.commentvideo(ce,vid));
		ks.sendDataToUser_CommentsTopic(ce.getUser().getUserId()+" commented on the video");
		ks.sendDataToUser_CommentsTopic(ce.toString());
		return "displaycomment";
	}
	// Unlike the video
	@GetMapping("/unlike/create/{id}")
	public String unlikeVideo(@PathVariable("id") int vid,likesEntity le, Model m){
		System.out.println(1);
		m.addAttribute("l1",le);
		m.addAttribute("id3",vid);
		return "createunlike";
	}
	//Collects unlike info
	@PostMapping("/unlike/collect/{id}")
	public String collectUnLike(@PathVariable("id") int vid, likesEntity le, Model m){
		m.addAttribute("str", us.unlikevideo(le,vid));
		ks.sendDataToUser_LikesTopic(le.getUser().getUserId()+"unliked the video");
		return "displaylike";
	}
	//Display comments of the video
	@GetMapping("/comments/view/{id}")
	public String viewComments(@PathVariable("id") int vid, Model m) {
		List<commentsEntity> ls = us.getcomments(vid);
		m.addAttribute("clist",ls);
		return "listcomments";
	}
	//Edit the comment
	@GetMapping("/comments/edit/{id}")
	public String editComment(@PathVariable("id") int cid, Model m) {
		commentsEntity ce = us.getcommentbyid(cid);
		m.addAttribute("c1",ce);
		m.addAttribute("cid", cid);
		return "editcomment";
	}
	//Collects edited info and updates
	@PostMapping("/comments/collect/{id}")
	public String collectEditedComment(@PathVariable("id") int cid, commentsEntity ce, Model m){
		us.editcomment(ce,cid);
		ks.sendDataToUser_CommentsTopic(ce.getUser().getUserId()+"uncommented the video");
		return "successcomment";
	}
	//Delete Comment for the video
	@GetMapping("/comments/delete/{id}")
	public String deleteComment(@PathVariable("id") int cid) {
		us.deletecomment(cid);
		return "deletecomment";
	}
	//Add rating to the video
	@GetMapping("/rate/create/{id}")
	public String rateVideo(@PathVariable("id") int vid,ratingEntity re, Model m){
		m.addAttribute("r",re);
		m.addAttribute("id4",vid);
		return "addrating";
	}
	//Collects rating info
	@PostMapping("/rate/collect/{id}")
	public String collecRating(@PathVariable("id") int vid, ratingEntity re, Model m){
		m.addAttribute("str1", us.ratevideo(re,vid));
		return "displayrating";
	}
}
// http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4
// http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4
// http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4
