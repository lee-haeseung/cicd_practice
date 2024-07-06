package com.umc.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDTO {
    private String content;
    private Long postId;
}
