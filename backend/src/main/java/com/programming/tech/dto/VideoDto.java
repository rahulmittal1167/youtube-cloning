package com.programming.tech.dto;

import com.programming.tech.model.Comment;
import com.programming.tech.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {

    @Id
    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
}
