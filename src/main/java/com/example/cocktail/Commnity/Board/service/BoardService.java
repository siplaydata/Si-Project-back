package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.dto.BoardDTO;
import com.example.cocktail.Commnity.Board.model.Board;

import java.util.List;

public interface BoardService {
    Board findBoardById(Long id);
    BoardDTO convertToDTO(Board board);
    List<BoardDTO> convertToDTOList(List<Board> foundBoards);
    void checkAuthorizationByUser(String author, String nickname);
    void createBoardLimit(String nickname);
    boolean checkUser(String nickname);

}
