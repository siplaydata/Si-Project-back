package com.example.cocktail.Commnity.Board.controller;

import com.example.cocktail.Commnity.Board.dto.BoardDTO;
import com.example.cocktail.Commnity.Board.exception.BoardException;
import com.example.cocktail.Commnity.Board.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private BoardException boardException;
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
        boardException.contentAndTitleNotEmpty(boardDTO.getTitle(), boardDTO.getContent());

        return postBoardService.createBoard(authentication.getName(), boardDTO.getTitle(), boardDTO.getContent());
    }
    @PutMapping("/{id}")
    public boolean updateBoard(@PathVariable Long id,
                               @RequestBody BoardDTO boardDTO,
                               Authentication authentication) {
        checkAuthentication(authentication);
        boardException.contentAndTitleNotEmpty(boardDTO.getTitle(), boardDTO.getContent());

        return putBoardService.updateBoard(id, authentication.getName(), boardDTO.getTitle(), boardDTO.getContent());
    }
    @DeleteMapping("/{id}")
    public boolean deleteBoard(@PathVariable Long id, Authentication authentication) {
        checkAuthentication(authentication);
        return deleteBoardService.deleteBoard(id, authentication.getName());
    }
    @GetMapping("/search")
    public List<BoardDTO> searchBoards(@RequestParam(required = false) String author,
                                       @RequestParam(required = false) String title,
                                       @RequestParam(required = false) String content) {
        if (author != null){ return getBoardService.searchByAuthor(author); }
        if (title != null){ return getBoardService.searchByTitle(title); }
        if (content != null){ return getBoardService.searchByContent(content); }
        else { return getAllBoards(); }
    }
}