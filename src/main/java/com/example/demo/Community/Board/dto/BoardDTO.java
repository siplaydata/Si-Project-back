package com.example.demo.Community.Board.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;
}
