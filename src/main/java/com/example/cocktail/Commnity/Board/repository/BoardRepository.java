package com.example.cocktail.Commnity.Board.repository;

import com.example.cocktail.Commnity.Board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    long countByAuthorAndCreatedAtBetween(String nickname, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Board> findByAuthorContaining(String author);
    List<Board> findByTitleContaining(String title);
    List<Board> findByContentContaining(String content);
}
