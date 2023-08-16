package com.example.demo.Community.Board.dto;

import com.example.demo.Community.Comment.dto.CommentDTO;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardDTO {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private List<CommentDTO> comments;

    public BoardDTO(Long id, String author, String title, String content, LocalDateTime createdAt, List<CommentDTO> comments) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
    }
}

