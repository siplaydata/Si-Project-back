package com.example.demo.Community.Board.service;

import com.example.demo.Community.Board.model.Board;


public interface BoardService {
    Board findBoardById(Long id);
    void checkAuthorizationByUser(String author, String nickname);
    boolean checkUser(String nickname);
}
