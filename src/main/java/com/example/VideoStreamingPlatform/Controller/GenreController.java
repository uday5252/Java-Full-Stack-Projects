package com.example.VideoStreamingPlatform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.VideoStreamingPlatform.Entity.GenreEntity;
import com.example.VideoStreamingPlatform.Service.GenreService;

@Controller
public class GenreController {
	
	@Autowired
	GenreService gs;
	
	//Adding Genre
	@GetMapping("api/admin/genres")
	public String displayGenre(Model m) {
		GenreEntity genre = new GenreEntity();
		m.addAttribute("genre", genre);
		return "GenreForm";
	}
	
	@PostMapping("/add/admin/genres")
	public String addGenre(GenreEntity genre) {
		gs.addGenre(genre);
		return "redirect:/homepage";
	}
	
	//Displaying list of all genres present
	
	@GetMapping("api/genres")
	public String getAllGenres(Model m) {
		List<GenreEntity> list = gs.getAllGenres();
		m.addAttribute("genres", list);
		return "genre_list";
		
	}
	
//	@GetMapping("api/genres")
//	public List<GenreEntity> getAllGenres(){
//		return gs.getAllGenres();
//	}
	
	//Update a Genre
	
	@GetMapping("/updateGenre/{genre_id}")
	public String displayUpdateGenre(@PathVariable("genre_id") int genre_id, Model m) {
		GenreEntity genre = gs.getGenreById(genre_id);
		m.addAttribute("genre", genre);
		return "UpdateGenre";
	}
	
	@PostMapping("api/admin/genres/{id}")
	public String updateGenre(@ModelAttribute GenreEntity genre, @PathVariable("id") int id) {
	    gs.updateGenre(genre, id);
	    return "redirect:/homepage";
	}

	//Delete a Genre
	
	@GetMapping("/deleteGenre")
	public String displayDeleteGenre() {
		
		return "DeleteGenre";
	}
	
	@PostMapping("api/admin/genres/delete")
	public String deleteGenre(@RequestParam("genre_id") int genre_id, RedirectAttributes redirectAttributes) {
		gs.deleteGenre(genre_id);
		redirectAttributes.addFlashAttribute("successMessage", "Genre Deleted Successfully!");
		return "redirect:/homepage";
	}
	
//	@DeleteMapping("api/admin/genres/{id}")
//	public String deleteGenre(@PathVariable("id") int id) {
//		gs.deleteGenre(id);
//		return "Genre Deleted";
//	}
}


