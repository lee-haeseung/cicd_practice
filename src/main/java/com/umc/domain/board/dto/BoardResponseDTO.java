package com.umc.domain.board.dto;

import com.umc.domain.board.entity.Board;
import com.umc.domain.board.entity.BoardStatus;
import com.umc.domain.post.dto.PostListResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String description;
    private BoardStatus status;
    private PostListResponseDTO postList;

    public BoardResponseDTO(Board board) {
        id = board.getId();
        title = board.getTitle();
        description = board.getDescription();
        status = board.getStatus();
        postList = new PostListResponseDTO(board.getPosts() != null ? board.getPosts() : new ArrayList<>());
    }
}
