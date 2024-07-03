package com.umc.domain.post.dto;

import com.umc.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostListResponseDTO {
    private Integer numberOfPosts;
    private List<ReducedPost> postList;

    @Getter
    @Setter
    @AllArgsConstructor
    private class ReducedPost {
        private Long id;
        private String title;
        private Long posterId;
        private String userNickname;
        private LocalDateTime createdAt;
    }

    public PostListResponseDTO(List<Post> postList) {
        this.postList = postList.stream()
                .map(post -> new ReducedPost(post.getId(), post.getTitle(), post.getPoster().getId(), post.getPoster().getNickname(), post.getCreatedAt()))
                .collect(Collectors.toList());
        this.numberOfPosts = postList.size();
    }

}
