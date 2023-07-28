package com.abc.demo.videostreaming.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.demo.videostreaming.Entity.Genre;
import com.abc.demo.videostreaming.Entity.Video;
import java.util.List;


public interface VideoInterfaceRepository extends JpaRepository<Video,Integer> {
    List<Video> findByTitle(String title);
    List<Video> findByGenre(Genre genre);
    List<Video> findByGenreId(int id);
}
