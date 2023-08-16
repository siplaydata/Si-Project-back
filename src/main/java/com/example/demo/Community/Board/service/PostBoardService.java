package com.example.demo.Community.Board.service;

import com.example.demo.Community.Board.exception.BoardExceptionHandler;
import com.example.demo.Community.Board.model.Board;
import com.example.demo.Community.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PostBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardExceptionHandler boardExceptionHandler;
    private void createBoardLimit(String nickname){
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startOfDay = currentDate.atStartOfDay();
        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MAX);

        long numberOfBoardsToday = boardRepository.countByAuthorAndCreatedAtBetween(nickname, startOfDay, endOfDay);

        if (numberOfBoardsToday >= 10) { throw new RuntimeException("하루에 10개 이상의 게시글을 작성할 수 없습니다."); }
    }
    public boolean createBoard(String nickname, String title, String content) {
        createBoardLimit(nickname);
        try {
            boardRepository.save(new Board(nickname, title, content));
            return true;
        } catch (Exception e) {
            boardExceptionHandler.failByError("등록", e.getMessage());
            return false;
        }
    }
}