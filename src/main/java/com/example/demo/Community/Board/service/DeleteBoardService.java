package com.example.demo.Community.Board.service;

import com.example.demo.Community.Board.exception.BoardExceptionHandler;
import com.example.demo.Community.Board.model.Board;
import com.example.demo.Community.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardExceptionHandler boardExceptionHandler;
    public boolean deleteBoard(Long id, String nickname) {
        Board board = boardService.findBoardById(id);
        boardService.checkAuthorizationByUser(board.getAuthor(), nickname);

        try {
            boardRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            boardExceptionHandler.failByError("삭제", e.getMessage());
            return false;
        }
    }
}
