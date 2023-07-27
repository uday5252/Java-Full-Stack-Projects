package com.abc.demo.Zee5.Apache.Kafka.Producer.SERVICE;

import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.GenreEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.UserEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.ENTITY.VideoEntity;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.GenreRepository;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.UserRepository;
import com.abc.demo.Zee5.Apache.Kafka.Producer.REPOSITORY.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    UserRepository userRepository;

    public void addVideo(VideoEntity videoData, int genre_id, int user_id){
        GenreEntity genre = genreRepository.findById(genre_id).get();
        videoData.setGenre_id(genre);
        UserEntity user = userRepository.findById(user_id).get();
        videoData.setUser(user);
        LocalDateTime now = LocalDateTime.now();
        videoData.setUploaded_at(now);
        videoRepository.save(videoData);
    }

    public List<VideoEntity> getAllVideos(){
       List<VideoEntity> videos = videoRepository.findAll();
       return videos;
    }

    public VideoEntity getVideo(int id){
        VideoEntity video = videoRepository.findById(id).get();
        return video;
    }


   public List<VideoEntity> getVideosByGenre(int genre_id){
       GenreEntity genre = genreRepository.findById(genre_id).get();
       List<VideoEntity>video = videoRepository.findAllByGenre(genre);
       return video;
   }

    public void updateVideoDetails(VideoEntity videoData, int id){
        VideoEntity video = videoRepository.findById(id).get();
        video.setVideo_title(videoData.getVideo_title() != null ? videoData.getVideo_title() : video.getVideo_title());
        video.setVideo_description(videoData.getVideo_description() != null ? videoData.getVideo_description() : video.getVideo_description());
        video.setVideo_url(videoData.getVideo_url() != null ? videoData.getVideo_url() : video.getVideo_url());
        videoRepository.save(video);
    }

    public void deleteVideo(int id){
        VideoEntity video = videoRepository.findById(id).get();
        videoRepository.deleteById(video.getVideo_id());
    }

    public String getUrl(int id){
        VideoEntity video = videoRepository.findById(id).get();
        String url = video.getVideo_url();
        return url;
    }


}
