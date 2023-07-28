package com.xyz.demo.EndProject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genreTable")
public class GenreEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int genreId;
   private String genreName;
   
   public int getGenreId() {
	return genreId;
}
public void setGenreId(int genreId) {
	this.genreId = genreId;
}
private String genreDescription;

   
public String getGenreName() {
	return genreName;
}
public void setGenreName(String genreName) {
	this.genreName = genreName;
}
public String getGenreDescription() {
	return genreDescription;
}
public void setGenreDescription(String genreDescription) {
	this.genreDescription = genreDescription;
}
   
@Override
public String toString() {
	return "GenreEntity [genreId=" + genreId + ", genreName=" + genreName + ", genreDescription=" + genreDescription
			+ "]";
}
  
}
