package com.example.cocktail.Commnity.Comment.controller;

import com.example.cocktail.Commnity.Board.controller.BoardController;
import com.example.cocktail.Commnity.Comment.dto.CommentDTO;
import com.example.cocktail.Commnity.Comment.exception.CommentExceptionHandler;
import com.example.cocktail.Commnity.Comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{boardId}/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentExceptionHandler commentExceptionHandler;
    @Autowired
    BoardController boardController;

    @PostMapping
    public boolean createComment(@PathVariable Long boardId,
                                 @RequestBody CommentDTO commentDTO,
                                 Authentication authentication ) {
        boardController.checkAuthentication(authentication);
        commentExceptionHandler.commentNotEmpty(commentDTO.getContent());

        return commentService.createComment(boardId, authentication.getName(), commentDTO.getContent());
    }
    @PutMapping("/{commentId}")
    public boolean updateComment(@PathVariable Long boardId,
                                 @PathVariable Long commentId,
                                 @RequestBody CommentDTO commentDTO,
                                 Authentication authentication) {
        boardController.checkAuthentication(authentication);
        commentExceptionHandler.commentNotEmpty(commentDTO.getContent());

        return commentService.updateComment(boardId, commentId, authentication.getName(), commentDTO.getContent());
    }
    @DeleteMapping("/{commentId}")
    public boolean deleteComment(@PathVariable Long boardId,
                                 @PathVariable Long commentId,
                                 Authentication authentication) {
        boardController.checkAuthentication(authentication);

        return commentService.deleteComment(boardId, commentId, authentication.getName());
    }
}
