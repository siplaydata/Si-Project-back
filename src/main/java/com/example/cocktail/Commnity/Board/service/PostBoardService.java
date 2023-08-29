package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.exception.BoardException;
import com.example.cocktail.Commnity.Board.model.Board;
import com.example.cocktail.Commnity.Board.repository.BoardRepository;
import com.example.cocktail.Commnity.Userinfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardException boardException;


    public boolean createBoard(String nickname, String title, String content) {
        try {
            boardService.createBoardLimit(nickname);
            boardRepository.save(new Board(nickname, title, content));
            return true;
        } catch (Exception e) {
            boardException.throwRuntimeException("등록", e.getMessage());
            return false;
        }
    }
}