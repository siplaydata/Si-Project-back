package com.example.demo.Community.Board.controller;

import com.example.demo.Community.Board.dto.BoardDTO;
import com.example.demo.Community.Board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    private void errorManager(Authentication authentication) {
        if(authentication == null || !boardService.checkUser(authentication)){
            throw new AccessDeniedException("회원만 이용 가능 합니다.");
        }
    }
    @GetMapping
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }
    @PostMapping
    public boolean createBoard(@RequestBody BoardDTO boardDTO, Authentication authentication) {
        errorManager(authentication);
        return boardService.createBoard(boardDTO, authentication);
    }
    @GetMapping("/{id}")
    public BoardDTO getBoard(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }
    @PutMapping("/{id}")
    public boolean updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO, Authentication authentication) {
        errorManager(authentication);
        return boardService.updateBoard(id, boardDTO, authentication);
    }
    @DeleteMapping("/{id}")
    public boolean deleteBoard(@PathVariable Long id, Authentication authentication) {
        errorManager(authentication);
        return boardService.deleteBoard(id, authentication);
    }
}

