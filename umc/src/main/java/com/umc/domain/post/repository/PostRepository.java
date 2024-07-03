package com.umc.domain.post.repository;

import com.umc.domain.board.entity.Board;
import com.umc.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    Page<Post> findAllOrderByCreatedAtDesc(PageRequest pageRequest);

    Page<Post> findPostsByBoardOrderByCreatedAtDesc(Board board, PageRequest pageRequest);

}
