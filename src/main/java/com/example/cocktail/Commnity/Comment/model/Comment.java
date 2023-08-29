package com.example.cocktail.Commnity.Comment.model;

import com.example.cocktail.Commnity.Board.model.Board;
import com.example.cocktail.Commnity.Userinfo.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 댓글 작성자

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
