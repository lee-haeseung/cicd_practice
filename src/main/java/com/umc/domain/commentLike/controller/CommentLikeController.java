package com.umc.domain.commentLike.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.commentLike.service.CommentLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments/{commentId}")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    // 프론트에서 보통 좋아요 버튼 하나로 좋아요/취소 를 수행하는데, 구분할 필요가 있나?
    @PutMapping("/like")
    public ApiResponse<String> likeButton(
            @Valid @PathVariable Long commentId) {
        return commentLikeService.likeOrCancel(commentId);
    }

}
