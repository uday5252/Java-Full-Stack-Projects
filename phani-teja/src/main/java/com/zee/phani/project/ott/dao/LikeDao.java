package com.zee.phani.project.ott.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zee.phani.project.ott.dto.LikeDto;
import com.zee.phani.project.ott.entity.LikeEntity;
import com.zee.phani.project.ott.entity.UserEntity;
import com.zee.phani.project.ott.entity.VideoEntity;
import com.zee.phani.project.ott.repository.LikeRepository;
import com.zee.phani.project.ott.repository.UserRepository;
import com.zee.phani.project.ott.repository.VideoRepository;

@Component
public class LikeDao {
    @Autowired
    LikeRepository repo;

    @Autowired
    VideoRepository vidRepo;

    @Autowired
    UserRepository userRepo;

    public LikeDto createLike(LikeDto likeDto) {
        LikeEntity likeEntity = new LikeEntity();
        UserEntity userEntity;
        VideoEntity videoEntity;

        // LikeEntity temp = repo.findByUserUserIdAndVideoId(likeDto.getUserId(),
        // likeDto.getVideoId()).get();

        try {
            userEntity = userRepo.findById(likeDto.getUserId()).get();
        } catch (Exception ec) {
            throw new NoSuchElementException("User not found with given ID");
        }

        try {
            videoEntity = vidRepo.findById(likeDto.getVideoId()).get();
        } catch (Exception ec) {
            throw new NoSuchElementException("User not found with given ID");
        }

        likeEntity.setUser(userEntity);
        likeEntity.setVideo(videoEntity);

        likeEntity = repo.save(likeEntity);
        return new LikeDto(likeEntity.getUser().getUserId(), likeEntity.getVideo().getId(), likeEntity);

    }

    public List<LikeDto> getAllLikes() {
        List<LikeEntity> likeEntities = repo.findAll();

        return likeEntities.stream().map(le -> new LikeDto(le.getUser().getUserId(), le.getVideo().getId(), le))
                .toList();
    }

    public void deleteLike(LikeDto likeDto) {
        LikeEntity temp = repo.findByUserUserIdAndVideoId(likeDto.getUserId(),
                likeDto.getVideoId()).get();

        repo.delete(temp);
    }

}
