package com.example.cocktail.Commnity.Board.exception;

import org.springframework.stereotype.Component;

@Component
public class BoardExceptionHandler {
    // TODO : QA 진행 해주세요 예외처리 찾아야해요
    public void contentAndTitleNotEmpty(String title, String content) {
        if (title.isEmpty()) { throw new IllegalArgumentException("제목을 입력 해주세요."); }
        if (content.isEmpty()) { throw new IllegalArgumentException("내용을 입력 해주세요."); }
    }

    public void failByError(String value, String errorMessage){
        throw new RuntimeException(value + "에 실패 했습니다. 에러 내용 : " + errorMessage);
    }
}
