package com.example.demo.Community.Board.repository;

import com.example.demo.Community.Board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    long countByAuthorAndCreatedAtBetween(String nickname, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
