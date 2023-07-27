package com.abc.demo.Zee5.OTT.Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.abc.demo.Zee5.OTT.Project.Entity.Genre;
import com.abc.demo.Zee5.OTT.Project.Service.GenreService;

@Controller
public class GenreController {
	@Autowired
	GenreService gs;
	@GetMapping("/api/admin/genre")
	public String displayUserform(Model m)
	{
		Genre genre=new Genre();
		m.addAttribute("genre",genre);
		return "displayGenreForm";
		
	}
	@PostMapping("/api/admin/creategenre")
	public String createUser(Genre genre)
	{
		gs.createGenre(genre);
		return "redirect:/home";
	}
	@GetMapping("/api/genres")
	public String listGenres(Model m)
	{
		List<Genre> genres=gs.listAllGenres();
		m.addAttribute("genres",genres);
		return "displayAllGenres";
	}
	@GetMapping("/api/admin/genresupdate/{genreId}")
	public String editGenreForm(@PathVariable("genreId")int id,Model m)
	{
		Genre genre=gs.findGenre(id);
		m.addAttribute("genre",genre);
		return "displayGenreEditform";
		
	}
	@PostMapping("/api/admin/genres/{genreId}")
	public String updateGenre(@PathVariable("genreId")int genreId,Genre genre)
	{
		gs.updateGenre(genreId,genre);
		return "redirect:/home";
	}
	@GetMapping("/api/admin/genres/{genreId}")
	public String deleteGenre(@PathVariable("genreId")int genreId)
	{
		gs.deleteGenre(genreId);
		return "redirect:/home";
	}
}

