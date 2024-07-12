package com.umc.domain.comment.entity;

import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.commentLike.entity.CommentLike;
import com.umc.domain.post.entity.Post;
import com.umc.domain.user.entity.Member;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member commenter;

    private String status;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CommentLike> commentLikes = new ArrayList<>();

}
