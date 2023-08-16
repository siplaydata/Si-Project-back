package com.example.demo.Community.Comment.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.demo.Community.Board.service.BoardService;
import com.example.demo.Community.Board.exception.BoardExceptionHandler;
import com.example.demo.Community.Comment.exception.CommentExceptionHandler;
import com.example.demo.Community.Comment.model.Comment;
import com.example.demo.Community.UserInfo.model.User;
import com.example.demo.Community.Comment.repository.CommentRepository;
import com.example.demo.Community.UserInfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentExceptionHandler commentExceptionHandler;
    public boolean createComment(Long boardId, String nickname, String content) {
        Comment comment = new Comment();
        comment.setBoard(boardService.findBoardById(boardId));
        comment.setUser(userRepository.findByNickname(nickname));
        comment.setContent(content);

        try {
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            commentExceptionHandler.failByError("댓글 작성", e.getMessage());
            return false;
        }
    }
    private Comment findCommentByIdAndUser(Long boardId, Long commentId, String nickname) {
        // 해당 사용자가 작성한 댓글 중에서 commentId와 일치하는 댓글을 찾아 리턴
        User user = userRepository.findByNickname(nickname);
        Comment comment = commentRepository.findByIdAndUser(commentId, user);

        if (comment == null || !comment.getBoard().getId().equals(boardId)) {
            throw new IllegalArgumentException("본인 외에는 할 수 없습니다.");
        }
        return comment;
    }
    public boolean updateComment(Long boardId, Long commentId, String nickname, String content) {
        Comment comment = findCommentByIdAndUser(boardId, commentId, nickname);
        try {
            comment.setContent(content);
            commentRepository.save(comment);
            return true;
        } catch (Exception e){
            commentExceptionHandler.failByError("댓글 수정", e.getMessage());
            return false;
        }
    }

    public boolean deleteComment(Long boardId, Long commentId, String nickname) {
        Comment comment = findCommentByIdAndUser(boardId, commentId, nickname);
        try {
            commentRepository.delete(comment);
            return true;
        } catch (Exception e) {
            commentExceptionHandler.failByError("댓글 삭제", e.getMessage());
            return false;
        }
    }
}
