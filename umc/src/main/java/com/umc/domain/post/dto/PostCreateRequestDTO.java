package com.umc.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequestDTO {
    private String title;
    private String content;
    private Long boardId;
    private Long posterId;
}
