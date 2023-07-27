package com.zee.Zee5Clone.Repository;

import com.zee.Zee5Clone.Entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {

//    @Modifying
//    @Transactional
//    @Query(value = "SELECT * FROM video WHERE genre_id=:genreId")
//    public List<Video> findVideoByGenre(@Param("genreId") int genreId);
    public List<Video> findBygenreId(int genreId);
}
