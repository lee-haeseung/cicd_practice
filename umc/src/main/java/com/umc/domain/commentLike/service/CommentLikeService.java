package com.umc.domain.commentLike.service;

import com.umc.common.exception.handler.CommentHandler;
import com.umc.common.exception.handler.UserHandler;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.comment.repository.CommentRepository;
import com.umc.domain.commentLike.dto.CommentLikeCreateRequestDTO;
import com.umc.domain.commentLike.entity.CommentLike;
import com.umc.domain.commentLike.repository.CommentLikeRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentLikeService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final CommentLikeRepository commentLikeRepository;

    public ApiResponse<String> likeOrCancel(Long commentId, CommentLikeCreateRequestDTO commentLikeCreateRequestDTO) {
        Member member = memberRepository.findById(commentLikeCreateRequestDTO.getMemberId()).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentHandler(ErrorCode.COMMENT_NOT_EXIST));

        if (commentLikeRepository.existsByMemberAndComment(member, comment)) {
            commentLikeRepository.deleteByMemberAndComment(member, comment);
            return ApiResponse.of(SuccessCode._OK, "댓글에 좋아요를 취소했습니다.");
        } else {
            CommentLike commentLike = CommentLike.builder()
                    .member(member)
                    .comment(comment)
                    .build();
            commentLikeRepository.save(commentLike);
            return ApiResponse.of(SuccessCode._OK, "댓글에 좋아요를 눌렀습니다.");
        }
    }
}
