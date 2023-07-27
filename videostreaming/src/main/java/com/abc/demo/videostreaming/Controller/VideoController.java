package com.abc.demo.videostreaming.Controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.demo.videostreaming.Entity.Video;
import com.abc.demo.videostreaming.EntityRepository.GenreInterfaceRepository;
import com.abc.demo.videostreaming.EntityRepository.VideoInterfaceRepository;
import com.abc.demo.videostreaming.Service.VideoService;
import com.abc.demo.videostreaming.doa.VideoDao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@Tag(
    name = "Video Controller",
    description = "Video Controller"
)
@RestController
public class VideoController {

    @Autowired
    VideoInterfaceRepository vir;

    @Autowired
    GenreInterfaceRepository git;

    @Autowired
    VideoService vs;


    private final String videoFolderPath = "/Users/harshavardhan.kumar/Desktop/Spring/videostreaming/src/main/resources/videos/";

    @Operation(
        description = "Add Video",
        summary = "Add Video"
    )
    @PostMapping("/api/admin/videos")
    void addVideo(@RequestBody VideoDao video){
        Video v = new Video();
        v.setTitle(video.getTitle());
        v.setGenre(vs.findgenre(video.getGenre()));
        v.setDescription(video.getDescription());
        v.setUrl(videoFolderPath+video.getTitle());
        v.setUploaded_at(Date.from(Instant.now()));
        v.setUser(vs.finduser(video.getUsername()));
        vs.save(v);
        vs.notifyUploads(v);
    }

    @Operation(
        description = "get Video",
        summary = "get Video"
    )
    @GetMapping(value = "/api//videos/{id}", produces = "video/mp4")
    Mono<Resource> streamVideo(@PathVariable("id") int id){
        String videoFilePath = vir.getReferenceById(id).getUrl();
        System.out.println(videoFilePath);
        Resource video = new FileSystemResource(videoFilePath);
        return Mono.just(video);
    }
    
    @Operation(
        description = "Get Videos by Genre",
        summary = "Get Videos by Genre"
    )
    @GetMapping("/api/genres/{genreName}/videos")
    String genreVideos(@PathVariable("genreName") String str){
        List<String> temp = vir.findByGenreId(git.findByName(str).getId()).stream().map(video->video.getTitle()).toList();
        return temp.toString();
    }

    @Operation(
        description = "Get All Videos"
    )
    @GetMapping("/api/videos")
    String allVideos(){
        // return vir.findAll().stream().map(video->{
        //     String str = video.getTitle()+" "+video.getDescription();
        //     return str;
        // }).toList().toString();
        return vir.findAll().stream().map(video->video.toString()).toList().toString();
    }

    @Operation(
        description = "Delete Video",
        summary = "Delete Video"
    )
    @DeleteMapping("/api/admin/videos/{videoId}")
    ResponseEntity<String> deleteVideo(@PathVariable("videoId") int id){
        vs.delete(vir.getReferenceById(id));
        return new ResponseEntity<String>("Deleted",HttpStatus.OK);
    }

    @Operation(
        description = "Update Video",
        summary = "Update Video"
    )
    @PutMapping("/api/admin/videos/{videoId}")
    ResponseEntity<String> updateVideo(@PathVariable("videoId") int id,@RequestBody VideoDao video){
        Video v = vir.getReferenceById(id);
        v.setDescription((video.getDescription()==null)? v.getDescription():video.getDescription());
        System.out.println(v.getGenre().getName());
        v.setGenre((video.getGenre()==null)? v.getGenre() : git.findByName(video.getGenre())
        );
        vs.save(v);

        return new ResponseEntity<String>("Updated",HttpStatus.ACCEPTED);

    }
}
