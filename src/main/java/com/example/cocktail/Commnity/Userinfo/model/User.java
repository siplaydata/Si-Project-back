package com.example.cocktail.Commnity.Userinfo.model;

import com.example.cocktail.Commnity.Comment.model.Comment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments; // 사용자가 작성한 댓글들
}
