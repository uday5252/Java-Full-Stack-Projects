package com.xyz.demo.EndProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.demo.EndProject.Entity.GenreEntity;
import com.xyz.demo.EndProject.Service.GenreService;

@Controller
@RequestMapping("/api")
public class GenreController {
	
	@Autowired
     GenreService genreService;

 
    @GetMapping("/genres")
    public String getAllGenres(Model m) {
        List<GenreEntity> genres = genreService.getAllGenres();
        m.addAttribute("genres",genres);
        return "showgenres";
    }

    @PostMapping("/admin/genres")
    public ResponseEntity<GenreEntity> addGenre(@RequestBody GenreEntity genre) {
        GenreEntity createdGenre = genreService.addGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @PutMapping("/admin/genres/{genreId}")
    public ResponseEntity<GenreEntity> updateGenre(@PathVariable int genreId, @RequestBody GenreEntity genre) {
        GenreEntity updatedGenre = genreService.updateGenre(genreId, genre);
        if (updatedGenre != null) {
            return ResponseEntity.ok(updatedGenre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/genres/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable int genreId) {
        boolean deleted = genreService.deleteGenre(genreId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
