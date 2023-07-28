package com.sivasai.endGame.SpringFinalProject.Controller;

import com.sivasai.endGame.SpringFinalProject.Entity.Genre;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.ResponseGenerator.ResponseHandler;
import com.sivasai.endGame.SpringFinalProject.Service.GenreService;
import com.sivasai.endGame.SpringFinalProject.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @Autowired
    VideoService videoService;

    @GetMapping()
    public ResponseEntity<?> getAllGenres(){
        List<Genre> genreList = genreService.getAllGenres();
        return ResponseHandler.generateResponse("Here is the list of genres", HttpStatus.OK, genreList);
    }

    @GetMapping("/{genreId}/videos")
    public ResponseEntity<?> getAllVideosOfGenre(@RequestParam("genreId")int genreId)
    {
        if(genreService.ifExists(genreId)){
            List<Video> videosList = videoService.getByGenre(genreId);
            if(videosList.isEmpty()){
                return ResponseHandler.generateResponse("No titles exists in our database for the given genre", HttpStatus.NOT_FOUND, null);
            }
            else {
                return ResponseHandler.generateResponse("Here is the list of movies for the requested genre", HttpStatus.OK, videosList.toString());
            }
        }
        else {
            return ResponseHandler.generateResponse("Genre does not exist",HttpStatus.NOT_FOUND, null);
        }
    }
}
