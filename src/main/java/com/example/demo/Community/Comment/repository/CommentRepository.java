package com.example.demo.Community.Comment.repository;

import com.example.demo.Community.Comment.model.Comment;
import com.example.demo.Community.UserInfo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByIdAndUser(Long commentId, User user);
}
