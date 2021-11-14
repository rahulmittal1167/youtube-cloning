package com.programming.tech.repository;

import com.programming.tech.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
