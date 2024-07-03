package com.umc.domain.comment.dto;

import com.umc.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CommentListPesponseDTO {
    private Integer numberOfComments;
    private List<CommentResponseDTO> commentList;

    public CommentListPesponseDTO(List<Comment> commentList) {
        this.commentList = commentList.stream()
                .map(CommentResponseDTO::new)
                .collect(Collectors.toList());
        this.numberOfComments = commentList.size();
    }
}