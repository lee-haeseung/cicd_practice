package com.umc.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequestDTO {
    private String content;
    private String status;
}
