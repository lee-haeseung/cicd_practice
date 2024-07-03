package com.umc.domain.postLike.service;

import com.umc.common.exception.handler.PostHandler;
import com.umc.common.exception.handler.UserHandler;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.postLike.dto.PostLikeCreateRequestDTO;
import com.umc.domain.postLike.entity.PostLike;
import com.umc.domain.postLike.repository.PostLikeRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostLikeService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;

    public ApiResponse<String> likeOrCancel(Long postId, PostLikeCreateRequestDTO postLikeCreateRequestDTO) {
        Member member = memberRepository.findById(postLikeCreateRequestDTO.getMemberId()).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorCode.POST_NOT_EXIST));

        if (postLikeRepository.existsByMemberAndPost(member, post)) {
            postLikeRepository.deleteByMemberAndPost(member, post);
            return ApiResponse.of(SuccessCode._OK, "게시물에 좋아요를 취소했습니다.");
        } else {
            PostLike postLike = PostLike.builder()
                    .member(member)
                    .post(post)
                    .build();
            postLikeRepository.save(postLike);
            return ApiResponse.of(SuccessCode._OK, "게시물에 좋아요를 눌렀습니다.");
        }
    }
}
