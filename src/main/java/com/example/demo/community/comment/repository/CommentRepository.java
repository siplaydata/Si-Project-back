package com.example.demo.community.comment.repository;

import com.example.demo.community.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // TODO: 필요한 추가 메서드 정의
}
