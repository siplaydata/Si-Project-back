package com.example.demo.Community.Comment.exception;

import org.springframework.stereotype.Component;

@Component
public class CommentExceptionHandler {
    // TODO : QA 진행 해주세요 예외처리 찾아야해요
    public void commentNotEmpty(String comment){
        if(comment.isEmpty()) {throw new IllegalArgumentException("댓글을 입력 해주세요.");}
    }
    public void failByError(String value, String errorMessage){
        throw new RuntimeException(value + "에 실패 했습니다. 에러 내용 : " + errorMessage);
    }
}
