package com.umc.domain.board.entity;

import com.umc.common.entity.BaseTimeEntity;
import com.umc.domain.post.entity.Post;
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
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'USE'")
    @Builder.Default
    private BoardStatus status = BoardStatus.USE;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Post> posts = new ArrayList<>();
}
