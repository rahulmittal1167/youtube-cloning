package com.programming.tech.service;

import com.programming.tech.dto.UploadVideoResponse;
import com.programming.tech.dto.VideoDto;
import com.programming.tech.model.Video;
import com.programming.tech.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile){

        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        //Upload file to AWS S3
        // Save Video Data to Database
       var savedVideo= videoRepository.save(video);
       return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
        //Find the video by video Id
        var savedVideo = getVideoById(videoDto.getId());
        // Map the videoDto fields to video
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());
        // Save the video to Database

        videoRepository.save(savedVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        var savedVideo = getVideoById(videoId);

        // Upload video in thumbnail
        String thumbnailUrl  = s3Service.uploadFile(file);

        savedVideo.setThumbnailUrl(thumbnailUrl);

        videoRepository.save(savedVideo);

        return thumbnailUrl;

    }


    Video getVideoById(String videoId){
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id -" + videoId));
    }
}
