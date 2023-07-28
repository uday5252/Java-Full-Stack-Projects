package com.abc.demo.videostreaming.doa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name="Video")
public class VideoDao {
    String title;
    String description;
    String genre;
    String username;
}
