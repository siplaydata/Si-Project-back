package com.example.demo.community.comment.service;

import com.example.demo.community.comment.model.Comment;
import com.example.demo.community.comment.repository.CommentRepository;
import com.example.demo.config.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 댓글 생성
    public Comment createComment(Long postId, Comment comment) {
        // TODO: 사용자 인증 및 권한 확인 로직 추가
        // 사용자가 댓글을 작성할 권한을 가지고 있는지 확인

        return commentRepository.save(comment);
    }

    // TODO: 다른 댓글 관련 서비스 메서드들 추가

    // TODO: 댓글 수정, 삭제 등의 기능도 구현 가능
}

