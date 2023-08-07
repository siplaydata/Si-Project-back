package com.example.demo.community.post.controller;

import com.example.demo.community.post.model.Post;
import com.example.demo.community.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("/write")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    // TODO : CR 개발 UD 해야함. C 한글 막힘 이유 모름
}
