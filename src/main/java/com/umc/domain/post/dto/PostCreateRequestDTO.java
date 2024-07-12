package com.umc.domain.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostCreateRequestDTO {
    private String title;
    private String content;
    private Long boardId;
    private MultipartFile postImage;
}
