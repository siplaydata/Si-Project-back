package com.example.cocktail.Commnity.Comment.repository;

import com.example.cocktail.Commnity.Comment.model.Comment;
import com.example.cocktail.Commnity.Userinfo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByIdAndUser(Long commentId, User user);
}

