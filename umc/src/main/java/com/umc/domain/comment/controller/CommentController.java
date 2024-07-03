package com.umc.domain.comment.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.comment.dto.CommentCreateRequestDTO;
import com.umc.domain.comment.dto.CommentListPesponseDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.dto.CommentUpdateRequestDTO;
import com.umc.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ApiResponse<CommentResponseDTO> createComment(@Valid @RequestBody CommentCreateRequestDTO commentCreateRequestDTO) {
        return commentService.createComment(commentCreateRequestDTO);
    }

    @DeleteMapping("/comments/{commentId}")
    public ApiResponse<String> deleteComment(@Valid @PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/comments/{commentId}")
    public ApiResponse<CommentResponseDTO> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentUpdateRequestDTO commentUpdateRequestDTO) {
        return commentService.updateComment(commentId, commentUpdateRequestDTO);
    }

    @GetMapping("/comments/{commentId}")
    public ApiResponse<CommentResponseDTO> getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @GetMapping("/posts/{postId}/comments")
    public ApiResponse<CommentListPesponseDTO> getCommentList(@PathVariable Long postId) {
        return commentService.getCommentList(postId);
    }
}
