package com.example.demo.Community.Board.service;

import com.example.demo.Community.Board.exception.BoardExceptionHandler;
import com.example.demo.Community.Board.model.Board;
import com.example.demo.Community.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PutBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardExceptionHandler boardExceptionHandler;
    public boolean updateBoard(Long id, String nickname, String title, String content) {
        Board board = boardService.findBoardById(id);
        boardService.checkAuthorizationByUser(board.getAuthor(), nickname);

        if (board.getTitle().equals(title) && board.getContent().equals(content)){
            throw new IllegalArgumentException("수정된 내용이 없습니다.");
        } else {
            try {
                board.setTitle(title);
                board.setContent(content);
                boardRepository.save(board);
                return true;
            } catch (Exception e) {
                boardExceptionHandler.failByError("수정", e.getMessage());
                return false;
            }
        }
    }
}
