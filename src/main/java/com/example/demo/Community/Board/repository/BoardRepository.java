package com.example.demo.Community.Board.repository;

import com.example.demo.Community.Board.dto.BoardDTO;
import com.example.demo.Community.Board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
