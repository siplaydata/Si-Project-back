package com.example.demo.community.post.service;

import com.example.demo.community.post.model.Post;
import com.example.demo.community.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts() {
        // 잘 받아 옴
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        // TODO : 영어는 저장됨, 한글이 안 됨 문제 해결할 것
        return postRepository.save(post);
    }
}
