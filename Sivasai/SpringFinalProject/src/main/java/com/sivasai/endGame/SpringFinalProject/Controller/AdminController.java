package com.sivasai.endGame.SpringFinalProject.Controller;

import com.sivasai.endGame.SpringFinalProject.Entity.Genre;
import com.sivasai.endGame.SpringFinalProject.Entity.Roles;
import com.sivasai.endGame.SpringFinalProject.Entity.User;
import com.sivasai.endGame.SpringFinalProject.Entity.Video;
import com.sivasai.endGame.SpringFinalProject.Producer.KafkaProducer;
import com.sivasai.endGame.SpringFinalProject.ResponseGenerator.ResponseHandler;
import com.sivasai.endGame.SpringFinalProject.Service.GenreService;
import com.sivasai.endGame.SpringFinalProject.Service.UserService;
import com.sivasai.endGame.SpringFinalProject.Service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    GenreService genreService;

    @Autowired
    VideoService videoService;

    @Autowired
    KafkaProducer kafkaProducer;

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        user.roleSet(Roles.ROLE_ADMIN);
        User u = userService.saveUser(user);
        return ResponseHandler.generateResponse("User saved successfully", HttpStatus.CREATED, u);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId")int userId){
        if(userService.existsById(userId)) {
            User user = userService.getUserById(userId);
            if(videoService.checkExistsByUser(user))
            {
                videoService.deleteByUser(user);
            }
            userService.deleteUser(userId);
            return ResponseHandler.generateResponse("User deleted successfully", HttpStatus.OK, true);
        }
        else {
            return ResponseHandler.generateResponse("user id does not exist", HttpStatus.NOT_FOUND, false);
        }
    }

    @PostMapping("/genres")
    public ResponseEntity<?> addGenre(@Valid @RequestBody Genre genre){
        genre.setName(genre.getName().toLowerCase());
        Genre genre1 = genreService.saveGenreRecord(genre);
        kafkaProducer.sendDataToGenreUpdates("Genre created: "+genre1);
        return ResponseHandler.generateResponse("Saved genre", HttpStatus.CREATED, genre1);
    }

    @PutMapping("/genres/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable int genreId, @RequestBody Genre genre) throws IllegalAccessException, NoSuchFieldException {
        Genre genre1 = genreService.updateGenre(genre, genreId);
        if(genre1 != null) {
            kafkaProducer.sendDataToGenreUpdates("Genre updated: "+genre1);
            return ResponseHandler.generateResponse("Update Successful", HttpStatus.OK, genre1);
        }
        else {
            return ResponseHandler.generateResponse("Couldn't update genre. Error in genre Id.", HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable int genreId){
        Boolean bool = genreService.ifExists(genreId);
        if(bool){
            videoService.deleteGenre(genreId); //delete genre references in video before deleting genre
            genreService.deleteGenre(genreId);
            kafkaProducer.sendDataToGenreUpdates("Genre with "+genreId+" deleted.");
            return ResponseHandler.generateResponse("Genre deleted successfully", HttpStatus.OK, true);
        }
        else {
            return ResponseHandler.generateResponse("No genre found", HttpStatus.NOT_FOUND, false);
        }
    }

    @PostMapping("/videos")
    public ResponseEntity<?> saveVideo(@Valid @RequestBody Video video, @RequestParam("genres") Set<String> genres){
        User user = video.getUploadedBy();
        User existingUser = userService.getUser(user.getUserName(), user.getPassword(), user.getEmail());
//        System.out.println(existingUser.getUserName());
        if(existingUser.getUserName() == "error"){
            return ResponseHandler.generateResponse("Entry not created. Error in user parameters", HttpStatus.NOT_ACCEPTABLE, null);
        }
        if(existingUser != null){
            genres = genres.stream().map(String::toLowerCase).collect(Collectors.toSet());
//            System.out.println(genres.toString());
            Set<Genre> genreSet = genreService.getByGenreName(genres);
            if(!(genreSet.isEmpty())){
                //if not empty
                video.setUploadedBy(existingUser);
                video.setGenre_id(genreSet);
                Video persistedEntity = videoService.saveVideo(video);
                kafkaProducer.sendDataToVideoUploads("Video uploaded: "+persistedEntity.toString());
//                System.out.println("reached here as well");
                return ResponseHandler.generateResponse("Video entry saved. Note that if any genre given by you are not seen here it may be due to the genre not existing in the set of genres or issue in parsing genre entered.", HttpStatus.CREATED, persistedEntity.toString());
            }
            else {
                return ResponseHandler.generateResponse("Video not saved because we are currently not hosting this genre movies", HttpStatus.NOT_ACCEPTABLE, false);
            }
        }
        else {
            return ResponseHandler.generateResponse("No entry created. User is not registered/error in user details.", HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/videos/{videoId}")
    public ResponseEntity<?> updateMovieDetails(@PathVariable("videoId")int videoId, @Valid @RequestBody Video video, @RequestParam(value = "genres", required = false)Set<String> genres) throws NoSuchFieldException, IllegalAccessException {
        if (videoService.ifExists(videoId)) {
            Video oGvideo = videoService.getVideoById(videoId);
            if (genres != null) {
                genres = genres.stream().map(String::toLowerCase).collect(Collectors.toSet());
                Set<Genre> genreSet = genreService.getByGenreName(genres);
                if (!genreSet.isEmpty()) {
                    Set<Genre> ogGenreSet = oGvideo.getGenre_id();
                    ogGenreSet.addAll(genreSet);
                    video.setGenre_id(ogGenreSet);
                }
            }
            else{
                video.setGenre_id(null);
            }
            video.setUploadedBy(null);
            Video video1 = videoService.updateVideo(video, videoId);
            return ResponseHandler.generateResponse("Video entry updated successfully. " +
                        "Note that if any genre given by you are not seen here it may be due " +
                        "to the genre not existing in the set of genres or issue in parsing genre entered.",
                HttpStatus.OK, video1.toString());
        }
        else {
            return ResponseHandler.generateResponse("video with the given Id does not exist", HttpStatus.NOT_FOUND, null);
        }
    }

    @DeleteMapping("/videos/{videoId}")
    public ResponseEntity<?> deleteById(@PathVariable("videoId")int videoId){
        if(videoService.ifExists(videoId)){
            videoService.deleteById(videoId);
            return ResponseHandler.generateResponse("Video successfully deleted", HttpStatus.OK, true);
        }
        else {
            return ResponseHandler.generateResponse("Error video Id not found", HttpStatus.NOT_FOUND, null);
        }
    }
}