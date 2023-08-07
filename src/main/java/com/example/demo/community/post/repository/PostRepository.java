package com.example.demo.community.post.repository;

import com.example.demo.community.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}