package com.example.cocktail.Commnity.Comment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;

    public CommentDTO(Long id, String author, String content, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
    }
}
