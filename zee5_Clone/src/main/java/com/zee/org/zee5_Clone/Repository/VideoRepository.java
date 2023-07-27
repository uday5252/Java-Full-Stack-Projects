package com.zee.org.zee5_Clone.Repository;

import com.zee.org.zee5_Clone.Entity.GenreTable;
import com.zee.org.zee5_Clone.Entity.VideoTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoTable,Integer> {

    List<VideoTable> findByGenre(GenreTable genr);

    //After findBy give joined column name by giving its 1st letter capital


}
