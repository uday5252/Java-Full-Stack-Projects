package com.zee.org.zee5_Clone.Controller;

import com.zee.org.zee5_Clone.Entity.GenreTable;
import com.zee.org.zee5_Clone.Kafka.KafkaProducer;
import com.zee.org.zee5_Clone.Repository.GenreRepository;
import com.zee.org.zee5_Clone.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController
{
    @Autowired
    GenreService Gs;
    @Autowired
    KafkaProducer Kp;
//    @Autowired
//    GenreRepository Gr;
    @GetMapping("/api/genres")
    public ResponseEntity<List<GenreTable>> viewAllGenres(){
        List<GenreTable> li= Gs.ViewAllGenres();
        return new ResponseEntity<>(li, HttpStatus.OK);
    }
    @PostMapping("/api/admin/genres")
    public ResponseEntity<String> addGenre(@RequestBody GenreTable genre){
        Gs.createGenre(genre);
        return new ResponseEntity<>("Updated Genre", HttpStatus.CREATED);

    }
    @PutMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<GenreTable> updateGenre(@PathVariable("genreId")int a,String name,String discription){
        GenreTable data=Gs.updateGenre(a,name,discription);

        Kp.sendGenreDataToTopic(data.getGenreid());

        return new ResponseEntity<>(data, HttpStatus.OK);

    }
//    @PutMapping("/api/admin/genres/{genreId}")
//    public ResponseEntity<GenreTable> update(@PathVariable("genreId") int a,GenreTable g){
//
//       GenreTable data=Gr.findById(a).get();
//       if(g.getName() != null){
//           data.setName(g.getName());
//       }
//       if(g.getDiscription() != null){
//           data.setDiscription(g.getDiscription());
//       }
//        return new ResponseEntity<>(data,HttpStatus.OK);
//    }

    @DeleteMapping("/api/admin/genres/{genreId}")
    public ResponseEntity<String> delGenre(@PathVariable("genreId") int a){
        Gs.DeleteGenre(a);
        return new ResponseEntity<>("Deleted Genre",HttpStatus.OK);
    }


}


//        3.PUT /api/admin/genres/{genreId}`: Update an existing genre (Admin only).
//        4. `DELETE /api/admin/genres/{genreId}`: Delete a genre (Admin only).