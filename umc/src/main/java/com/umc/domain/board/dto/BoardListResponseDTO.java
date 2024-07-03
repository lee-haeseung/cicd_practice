package com.umc.domain.board.dto;

import com.umc.domain.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardListResponseDTO {
    private Integer numberOfBoards;
    private List<ReducedBoardDTO> boardList;

    public BoardListResponseDTO(List<Board> boardList) {
        this.boardList = boardList.stream()
                .map(board -> new ReducedBoardDTO(board.getId(), board.getTitle()))
                .collect(Collectors.toList());
        this.numberOfBoards = boardList.size();
    }
}
