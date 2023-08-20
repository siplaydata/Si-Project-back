package com.example.cocktail.Commnity.Board.service;

import com.example.cocktail.Commnity.Board.dto.BoardDTO;
import com.example.cocktail.Commnity.Board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    public List<BoardDTO> getAllBoards() {
        return boardService.convertToDTOList(boardRepository.findAll());
    }
    public BoardDTO getBoardById(Long id) {
        return boardService.convertToDTO(boardService.findBoardById(id));
    }
    public List<BoardDTO> searchByAuthor(String author) {
        return boardService.convertToDTOList(boardRepository.findByAuthorContaining(author));
    }
    public List<BoardDTO> searchByTitle(String title) {
        return boardService.convertToDTOList(boardRepository.findByTitleContaining(title));
    }
    public List<BoardDTO> searchByContent(String content) {
        return boardService.convertToDTOList(boardRepository.findByContentContaining(content));
    }
}
