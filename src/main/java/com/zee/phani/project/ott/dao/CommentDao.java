package com.zee.phani.project.ott.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zee.phani.project.ott.dto.CommentDto;
import com.zee.phani.project.ott.entity.CommentEntity;
import com.zee.phani.project.ott.entity.UserEntity;
import com.zee.phani.project.ott.entity.VideoEntity;
import com.zee.phani.project.ott.repository.CommentRepository;
import com.zee.phani.project.ott.repository.UserRepository;
import com.zee.phani.project.ott.repository.VideoRepository;

@Component
public class CommentDao {
    @Autowired
    CommentRepository repo;

    @Autowired
    VideoRepository vidRepo;

    @Autowired
    UserRepository userRepo;

    public CommentDto createComment(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        UserEntity userEntity;
        VideoEntity videoEntity;

        // CommentEntity temp = repo.findByUserUserIdAndVideoId(CommentDto.getUserId(),
        // CommentDto.getVideoId()).get();

        try {
            userEntity = userRepo.findById(commentDto.getUserId()).get();
        } catch (Exception ec) {
            throw new NoSuchElementException("User not found with given ID");
        }

        try {
            videoEntity = vidRepo.findById(commentDto.getVideoId()).get();
        } catch (Exception ec) {
            throw new NoSuchElementException("User not found with given ID");
        }

        commentEntity.setUser(userEntity);
        commentEntity.setVideo(videoEntity);
        commentEntity.setDescription(commentDto.getDescription());

        commentEntity = repo.save(commentEntity);
        return new CommentDto(commentEntity.getUser().getUserId(), commentEntity.getVideo().getId(),
                commentEntity.getDescription(), commentEntity);
    }

    public List<CommentDto> getAllComments() {
        List<CommentEntity> commentEntities = repo.findAll();

        return commentEntities.stream()
                .map(ce -> new CommentDto(ce.getUser().getUserId(), ce.getVideo().getId(), ce.getDescription(), ce))
                .toList();
    }

    public void deleteComment(CommentDto commentDto) {
        CommentEntity temp = repo.findByUserUserIdAndVideoId(commentDto.getUserId(),
                commentDto.getVideoId()).get();

        repo.delete(temp);
    }

}
