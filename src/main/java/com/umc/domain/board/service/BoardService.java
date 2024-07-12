package com.umc.domain.board.service;

import com.umc.common.exception.handler.BoardHandler;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.board.dto.BoardCreateRequestDTO;
import com.umc.domain.board.dto.BoardListResponseDTO;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.dto.BoardUpdateRequestDTO;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.entity.BoardStatus;
import com.umc.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ApiResponse<BoardResponseDTO> createBoard(BoardCreateRequestDTO boardCreateRequestDTO) {
        if (!boardRepository.findByTitle(boardCreateRequestDTO.getTitle()).isEmpty()) {
            throw new BoardHandler(ErrorCode.BOARD_ALREADY_EXIST);
        }

        Board board = Board.builder()
                .title(boardCreateRequestDTO.getTitle())
                .description(boardCreateRequestDTO.getDescription())
                .posts(new ArrayList<>())
                .build();
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(boardRepository.save(board));

        return ApiResponse.onSuccess(boardResponseDTO);
    }

    public ApiResponse<String> deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
        boardRepository.delete(board);

        return ApiResponse.of(SuccessCode._OK, "게시판이 성공적으로 삭제되었습니다.");
    }

    public ApiResponse<BoardListResponseDTO> getBoardList(String title, String statusString) {
        if (title == null && statusString == null) {
            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(boardRepository.findAll());
            return ApiResponse.onSuccess(boardListResponseDTO);
        }
        if (title != null) {
            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(boardRepository.findBoardsByTitle(title));
            return ApiResponse.onSuccess(boardListResponseDTO);
        } else {
            BoardStatus boardStatus = BoardStatus.fromString(statusString);
            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(boardRepository.findBoardsByStatus(boardStatus));
            return ApiResponse.onSuccess(boardListResponseDTO);
        }

    }

    public ApiResponse<BoardResponseDTO> getBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(board);

        return ApiResponse.onSuccess(boardResponseDTO);
    }

    public ApiResponse<BoardResponseDTO> updateBoard(Long id, BoardUpdateRequestDTO boardUpdateRequestDTO) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));

        // 이미 존재하는 이름으로 바꾸는지 확인
        if (!boardRepository.findByTitle(boardUpdateRequestDTO.getTitle()).isEmpty()) {
            if (boardRepository.findByTitle(boardUpdateRequestDTO.getTitle()) != boardRepository.findById(id))
                throw new BoardHandler(ErrorCode.BOARD_ALREADY_EXIST);
        }

        if (boardUpdateRequestDTO.getTitle() != "") {
            board.setTitle(boardUpdateRequestDTO.getTitle());
        }
        if (boardUpdateRequestDTO.getDescription() != "") {
            board.setDescription(boardUpdateRequestDTO.getDescription());
        }
        if (boardUpdateRequestDTO.getStatus() != "") {
            board.setStatus(BoardStatus.fromString(boardUpdateRequestDTO.getStatus()));
        }

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(boardRepository.save(board));
        return ApiResponse.onSuccess(boardResponseDTO);
    }
}
