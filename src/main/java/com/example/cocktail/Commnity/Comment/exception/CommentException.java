package com.example.cocktail.Commnity.Comment.exception;

import org.springframework.stereotype.Component;

@Component
public class CommentException {
    // TODO : QA 진행 해주세요 예외처리 찾아야해요
    public void commentNotEmpty(String comment){
        if(comment.isEmpty()) {throw new IllegalArgumentException("댓글을 입력 해주세요.");}
    }
    public void throwRuntimeException(String value, String errorMessage){
        throw new RuntimeException(value + "에 실패 했습니다. 에러 내용 : " + errorMessage);
    }
    public void throwIllegalArgumentException(String value){
        throw new IllegalArgumentException("본인 외에는 할 수 없습니다.");
    }
}
