package com.example.demo.community.comment.model;

import com.example.demo.community.post.model.Post;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 생성일시 등의 필드도 추가 가능

    // TODO: 댓글과 게시글 간의 관계 설정을 위한 코드 추가 (ManyToOne, JoinColumn 등)
    // private Long postId;

    // TODO: 필요한 추가 속성들을 정의

    // 생성일시 등의 필드도 추가 가능
}
