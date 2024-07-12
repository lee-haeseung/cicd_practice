package com.umc.domain.comment.dto;

import com.umc.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDTO {
    private Long id;
    private Long postId;
    private String content;
    private Integer likes;
    private Long commenterId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDTO(Comment comment) {
        id = comment.getId();
        postId = comment.getPost().getId();
        content = comment.getContent();
        commenterId = comment.getCommenter().getId();
        status = comment.getStatus();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
        likes = comment.getCommentLikes().size();
    }
}
