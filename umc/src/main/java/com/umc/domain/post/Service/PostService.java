package com.umc.domain.post.Service;

import com.umc.common.exception.handler.BoardHandler;
import com.umc.common.exception.handler.PostHandler;
import com.umc.common.exception.handler.UserHandler;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.post.dto.PostCreateRequestDTO;
import com.umc.domain.post.dto.PostListResponseDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.PostUpdateRequestDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public ApiResponse<PostResponseDTO> createPost(PostCreateRequestDTO postCreateRequestDTO) {
        Board board = boardRepository.findById(postCreateRequestDTO.getBoardId()).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
        Member member = memberRepository.findById(postCreateRequestDTO.getPosterId()).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));

        Post post = Post.builder()
                .title(postCreateRequestDTO.getTitle())
                .content(postCreateRequestDTO.getContent())
                .board(board)
                .poster(member)
                .comments(new ArrayList<>())
                .status("AVAILABLE")
                .build();

        PostResponseDTO postResponseDTO = new PostResponseDTO(postRepository.save(post));

        return ApiResponse.onSuccess(postResponseDTO);
    }

    public ApiResponse<String> deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostHandler(ErrorCode.POST_NOT_EXIST));
        postRepository.delete(post);

        return ApiResponse.of(SuccessCode._OK, "게시물이 성공적으로 삭제되었습니다.");
    }

    public ApiResponse<PostResponseDTO> getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostHandler(ErrorCode.POST_NOT_EXIST));

        PostResponseDTO postResponseDTO = new PostResponseDTO(post);
        return ApiResponse.onSuccess(postResponseDTO);
    }

    public ApiResponse<PostListResponseDTO> searchPosts(Integer page) {
        PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findAllOrderByCreatedAtDesc(PageRequest.of(page, 10)).getContent());
        return ApiResponse.onSuccess(postListResponseDTO);
    }

    public ApiResponse<PostListResponseDTO> searchPostsInBoard(Long boardId, Integer page) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
        PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardOrderByCreatedAtDesc(board, PageRequest.of(page, 10)).getContent());
        return ApiResponse.onSuccess(postListResponseDTO);
    }

    public ApiResponse<PostResponseDTO> updatePost(Long postId, PostUpdateRequestDTO postUpdateRequestDTO) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostHandler(ErrorCode.POST_NOT_EXIST));
        if (postUpdateRequestDTO.getTitle() != "") {
            post.setTitle(postUpdateRequestDTO.getTitle());
        }
        if (postUpdateRequestDTO.getContent() != "") {
            post.setContent(postUpdateRequestDTO.getContent());
        }
        if (postUpdateRequestDTO.getStatus() != "") {
            post.setStatus(postUpdateRequestDTO.getStatus());
        }
        post.setBoard(boardRepository.findById(postUpdateRequestDTO.getBoardId()).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST)));

        PostResponseDTO postResponseDTO = new PostResponseDTO(postRepository.save(post));
        return ApiResponse.onSuccess(postResponseDTO);
    }
}
