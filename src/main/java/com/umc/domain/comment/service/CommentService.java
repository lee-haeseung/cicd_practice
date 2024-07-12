package com.umc.domain.comment.service;

import com.umc.common.exception.handler.CommentHandler;
import com.umc.common.exception.handler.PostHandler;
import com.umc.common.exception.handler.UserHandler;
import com.umc.common.jwt.SecurityUtil;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.comment.dto.CommentCreateRequestDTO;
import com.umc.domain.comment.dto.CommentListPesponseDTO;
import com.umc.domain.comment.dto.CommentResponseDTO;
import com.umc.domain.comment.dto.CommentUpdateRequestDTO;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.comment.repository.CommentRepository;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public ApiResponse<CommentResponseDTO> createComment(CommentCreateRequestDTO commentCreateRequestDTO) {
        Member commenter = memberRepository.findByEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(() -> new UserHandler(ErrorCode._UNAUTHORIZED));
        Post post = postRepository.findById(commentCreateRequestDTO.getPostId()).orElseThrow(() -> new PostHandler(ErrorCode.POST_NOT_EXIST));

        Comment comment = Comment.builder()
                .status("AVAILABLE")
                .post(post)
                .commenter(commenter)
                .content(commentCreateRequestDTO.getContent())
                .build();

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(commentRepository.save(comment));
        return ApiResponse.onSuccess(commentResponseDTO);
    }

    public ApiResponse<String> deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentHandler(ErrorCode.COMMENT_NOT_EXIST));
        commentRepository.delete(comment);

        return ApiResponse.of(SuccessCode._OK, "댓글이 정상적으로 삭제되었습니다.");
    }

    public ApiResponse<CommentResponseDTO> updateComment(Long id, CommentUpdateRequestDTO commentUpdateRequestDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentHandler(ErrorCode.COMMENT_NOT_EXIST));
        if (commentUpdateRequestDTO.getStatus() != "") {
            comment.setStatus(commentUpdateRequestDTO.getStatus());
        }
        if (commentUpdateRequestDTO.getContent() != "") {
            comment.setContent(commentUpdateRequestDTO.getContent());
        }

        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(commentRepository.save(comment));
        return ApiResponse.onSuccess(commentResponseDTO);
    }

    public ApiResponse<CommentResponseDTO> getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentHandler(ErrorCode.COMMENT_NOT_EXIST));
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment);
        return ApiResponse.onSuccess(commentResponseDTO);
    }

    public ApiResponse<CommentListPesponseDTO> getCommentList(Long postId) {
        List<Comment> commentList = commentRepository.findAll();
        CommentListPesponseDTO commentListPesponseDTO = new CommentListPesponseDTO(commentList);
        return ApiResponse.onSuccess(commentListPesponseDTO);
    }
}
