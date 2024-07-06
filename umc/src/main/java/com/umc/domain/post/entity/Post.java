package com.umc.domain.post.entity;


import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.board.entity.Board;
import com.umc.domain.comment.entity.Comment;
import com.umc.domain.postLike.entity.PostLike;
import com.umc.domain.user.entity.Member;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member poster;

    private String status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "image_id")
    private PostImage postImage;
}
