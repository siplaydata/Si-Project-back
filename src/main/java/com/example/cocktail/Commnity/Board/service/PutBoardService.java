package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.exception.BoardException;
import com.example.cocktail.Commnity.Board.model.Board;
import com.example.cocktail.Commnity.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PutBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardException boardException;
    public boolean updateBoard(Long id, String nickname, String title, String content) {
        Board board = boardService.findBoardById(id);
        boardService.checkAuthorizationByUser(board.getAuthor(), nickname);

        if (board.getTitle().equals(title) && board.getContent().equals(content)){
            boardException.throwIllegalArgumentException("수정된 내용이 없습니다.");
        } else {
            try {
                board.setTitle(title);
                board.setContent(content);
                boardRepository.save(board);
                return true;
            } catch (Exception e) {
                boardException.throwRuntimeException("수정", e.getMessage());
                return false;
            }
        }
        return false;
    }
}
