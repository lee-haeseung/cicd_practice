package com.umc.domain.board.repository;

import com.umc.domain.board.entity.Board;
import com.umc.domain.board.entity.BoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBoardsByTitle(String title);

    List<Board> findBoardsByStatus(BoardStatus status);

    Optional<Board> findByTitle(String title);
}
