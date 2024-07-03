package com.umc.domain.postLike.repository;

import com.umc.domain.post.entity.Post;
import com.umc.domain.postLike.entity.PostLike;
import com.umc.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Boolean existsByMemberAndPost(Member member, Post post);

    void deleteByMemberAndPost(Member member, Post post);
}
