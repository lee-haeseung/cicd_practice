package com.umc.domain.commentLike.repository;

import com.umc.domain.comment.entity.Comment;
import com.umc.domain.commentLike.entity.CommentLike;
import com.umc.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Boolean existsByMemberAndComment(Member member, Comment comment);

    void deleteByMemberAndComment(Member member, Comment comment);
}
