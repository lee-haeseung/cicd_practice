package com.umc.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDTO {
    private String title;
    private String content;
    private String status;
    private Long boardId;
}
