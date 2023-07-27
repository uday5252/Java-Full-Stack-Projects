package com.zee.org.zee5_Clone.Controller;


import com.zee.org.zee5_Clone.Entity.VideoTable;
import com.zee.org.zee5_Clone.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class VideoController
{
    @Autowired
    VideoService Vs;

    @PostMapping("/api/admin/videos")
    public ResponseEntity<String> uploadNewVideo(@RequestBody VideoTable video){
        Vs.uploadVideo(video);
        return new ResponseEntity<>("video got uploaded", HttpStatus.CREATED);
    }
    @GetMapping("/api/videos/{videoId}")
    public ResponseEntity<VideoTable> getSpecificVideo(@PathVariable("videoId") int vid){
        VideoTable data=Vs.viewVideo(vid);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/api/videos")
    public ResponseEntity<List<VideoTable>> viewVideos(){
        List<VideoTable> li=Vs.viewAllVideos();
        return new ResponseEntity<>(li,HttpStatus.OK);
    }

    @GetMapping("/api/genres/{genreId}/videos")
    public ResponseEntity<List<VideoTable>> viewVideosByGenre(@PathVariable("genreId")int Gid){

        List<VideoTable> li=Vs.viewVideoByGenreId(Gid);
        return new ResponseEntity<>(li,HttpStatus.OK);
    }


    @PutMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> UpdateVideoByVideoId(@PathVariable("videoId") int Vid,VideoTable data){

        Vs.updateVideo(Vid,data);
        return new ResponseEntity<>("updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/videos/{videoId}")
    public ResponseEntity<String> UpdateVideoByVideoId(@PathVariable("videoId") int Vid){
        Vs.delVideoWithVideoId(Vid);
        return new ResponseEntity<>("Deleted video with videoid "+Vid+ "deleted Successfully",HttpStatus.OK);
    }


}


//        1. `POST /api/admin/videos`: Upload a new video and associate it with a genre (Admin only).
//        2. `GET /api/videos/{videoId}`: Get details of a specific video.
//        3. `GET /api/videos`: Get a list of all videos.
//        4. `GET /api/genres/{genreId}/videos`: Get a list of videos within a specific genre.
//        5. `PUT /api/admin/videos/{videoId}`: Update video details (Admin only).
//        6. `DELETE /api/admin/videos/{videoId}`: Delete a video (Admin only).