package com.example.demo.ZeeVideoStreaming.SERVICE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.ZeeVideoStreaming.ENTITY.GenreEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.UserEntity;
import com.example.demo.ZeeVideoStreaming.ENTITY.VideoEntity;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.GenreRepositoryInterface;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.UserRepositoryInterface;
import com.example.demo.ZeeVideoStreaming.REPOSITORY.VideoRepositoryInterface;

@Service
public class VideoService {
	@Autowired
	VideoRepositoryInterface vri;
	@Autowired
	UserRepositoryInterface uri;

	@Autowired
	GenreRepositoryInterface gri;

	@Autowired
	KafkaTemplate<Integer, String> kt;

	public void addVideoIntoDB(VideoEntity v) {
		UserEntity user = uri.findById(v.getUser().getId()).get();
		v.setUser(user);

		GenreEntity genre = gri.findById(v.getGenre().getId()).get();

		v.setGenre(genre);

		vri.save(v);

		VideoEntity v2 = vri.findById(v.getId()).get();
		kt.send("video_uploads", v2.toString());
	}

	public VideoEntity getVideoFromDB(long id) {
		return vri.findById(id).get();
	}

	public List<VideoEntity> getAllVideosFromDB() {
		return vri.findAll();
	}

	public VideoEntity updateVideoInDB(long id, VideoEntity updatedVideo) {
		VideoEntity videoFromDB = vri.findById(id).get();
		if (updatedVideo.getTitle() != null)
			videoFromDB.setTitle(updatedVideo.getTitle());
		if (updatedVideo.getDescription() != null)
			videoFromDB.setDescription(updatedVideo.getDescription());
		if (updatedVideo.getUrl() != null)
			videoFromDB.setUrl(updatedVideo.getUrl());
		vri.save(videoFromDB);

		String data = String.format("Video \"%1$s\" updated. \n%2$s", videoFromDB.getTitle(),
				videoFromDB.toString());

		kt.send("video_uploads", data);

		return videoFromDB;
	}

	public boolean deleteVideoInDB(long id) {
		VideoEntity videoFromDB = vri.findById(id).get();
		if (videoFromDB != null) {
			vri.delete(videoFromDB);
			String data = String.format("Video \"%1$s\" got deleted. \n%2$s", videoFromDB.getTitle(),
					videoFromDB.toString());

			kt.send("video_uploads", data);
			return true;
		}
		return false;
	}

	public List<VideoEntity> getVideosByGenreFromDB(long gid) {
		return vri.findByGenreId(gid);
	}

}
