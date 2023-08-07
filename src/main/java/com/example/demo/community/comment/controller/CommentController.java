package com.example.demo.community.comment.controller;

import com.example.demo.community.comment.model.Comment;
import com.example.demo.community.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.createComment(postId, comment);
    }
    // TODO: 댓글 조회, 생성, 수정, 삭제와 관련된 엔드포인트 추가
}

