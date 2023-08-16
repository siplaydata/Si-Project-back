package com.example.demo.Community.Board.controller;

import com.example.demo.Community.Board.dto.BoardDTO;
import com.example.demo.Community.Board.exception.BoardExceptionHandler;
import com.example.demo.Community.Board.service.*;
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
    @Autowired
    private DeleteBoardService deleteBoardService;
    @Autowired
    private GetBoardService getBoardService;
    @Autowired
    private PostBoardService postBoardService;
    @Autowired
    private PutBoardService putBoardService;
    @Autowired
    private BoardExceptionHandler boardExceptionHandler;
    public void checkAuthentication(Authentication authentication) {
        if(authentication == null ||
                !boardService.checkUser(authentication.getName())){
            throw new AccessDeniedException("회원만 이용 가능 합니다."); }
    }
    // TODO : JWT 로그인 시간 만료 처리 로직 작성 나중에 해야함. 예외 처리.
    @GetMapping
    public List<BoardDTO> getAllBoards() { return getBoardService.getAllBoards(); }
    @GetMapping("/{id}")
    public BoardDTO getBoard(@PathVariable Long id) { return getBoardService.getBoardById(id); }

    @PostMapping
    public boolean createBoard(@RequestBody BoardDTO boardDTO,
                               Authentication authentication) {
        checkAuthentication(authentication);
        boardExceptionHandler.contentAndTitleNotEmpty(boardDTO.getTitle(), boardDTO.getContent());

        return postBoardService.createBoard(authentication.getName(), boardDTO.getTitle(), boardDTO.getContent());
    }
    @PutMapping("/{id}")
    public boolean updateBoard(@PathVariable Long id,
                               @RequestBody BoardDTO boardDTO,
                               Authentication authentication) {
        checkAuthentication(authentication);
        boardExceptionHandler.contentAndTitleNotEmpty(boardDTO.getTitle(), boardDTO.getContent());

        return putBoardService.updateBoard(id, authentication.getName(), boardDTO.getTitle(), boardDTO.getContent());
    }
    @DeleteMapping("/{id}")
    public boolean deleteBoard(@PathVariable Long id, Authentication authentication) {
        checkAuthentication(authentication);
        return deleteBoardService.deleteBoard(id, authentication.getName());
    }
}