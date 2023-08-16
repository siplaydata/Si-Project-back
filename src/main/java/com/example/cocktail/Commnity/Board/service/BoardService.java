package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.model.Board;

public interface BoardService {
    Board findBoardById(Long id);
    void checkAuthorizationByUser(String author, String nickname);
    boolean checkUser(String nickname);
}
