package com.sivasai.endGame.SpringFinalProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VideoStreamingService {

    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(String url){
        return Mono.fromSupplier(() -> this.resourceLoader.getResource(url));
    }

}
