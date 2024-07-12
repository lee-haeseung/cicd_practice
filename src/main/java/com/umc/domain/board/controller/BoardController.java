package com.umc.domain.board.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardCreateRequestDTO;
import com.umc.domain.board.dto.BoardListResponseDTO;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.dto.BoardUpdateRequestDTO;
import com.umc.domain.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping //생성
    public ApiResponse<BoardResponseDTO> createBoard(@Valid @RequestBody BoardCreateRequestDTO boardCreateRequestDTO) {
        return boardService.createBoard(boardCreateRequestDTO);
    }

    @DeleteMapping("/{boardId}") // 삭제
    public ApiResponse<String> deleteBoard(@Valid @PathVariable Long boardId) {
        return boardService.deleteBoard(boardId);
    }

    @GetMapping // 전체 조회(조건 추가 가능)
    public ApiResponse<BoardListResponseDTO> getAllBoardList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String status
    ) {
        return boardService.getBoardList(title, status);
    }

    @GetMapping("/{boardId}") // 하나 조회
    public ApiResponse<BoardResponseDTO> getBoard(@Valid @PathVariable Long boardId) {
        return boardService.getBoardById(boardId);
    }

    @PutMapping("/{boardId}")
    public ApiResponse<BoardResponseDTO> updateBoard(
            @Valid @PathVariable Long boardId,
            @Valid @RequestBody BoardUpdateRequestDTO boardUpdateRequestDTO) {
        return boardService.updateBoard(boardId, boardUpdateRequestDTO);
    }
}
