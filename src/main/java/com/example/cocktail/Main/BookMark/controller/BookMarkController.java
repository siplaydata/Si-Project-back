package com.example.cocktail.Main.BookMark.controller;

import com.example.cocktail.Main.BookMark.dto.BookMarkDTO;
import com.example.cocktail.Main.BookMark.model.BookMark;
import com.example.cocktail.Main.BookMark.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookMarkController {
    @Autowired
    private BookMarkService bookMarkService;
    public void checkAuthentication(Authentication authentication) {
        if(authentication == null ||
                !bookMarkService.checkUser(authentication.getName())){
            throw new AccessDeniedException("회원만 이용 가능 합니다."); }
    }

    @PostMapping("{id}") // 이 아이디는 레시피 아이디
    public void bookMark(@PathVariable int id,
                         Authentication authentication,
                         @RequestParam boolean type){
        // type 은 boolean으로 즐찾버튼 마냥 버튼 클릭시마다 true가 저장 false가 삭제 지금 만들면서 느낀건데 다시 게시판 좋아요 싫어요도 할 수 있을 듯

        checkAuthentication(authentication);

        bookMarkService.createBookMarkAndDelete(id, authentication.getName(), type);
    }

    @GetMapping // 겟 요청 하면 헤더 읽어 들어와서 회원 누구인지 자동으로 알아서 레시피 디비 조회 해서 return 해 줌
    public List<BookMarkDTO> getBookMark(Authentication authentication){
        checkAuthentication(authentication);
        return bookMarkService.getBookMarkList(authentication.getName());
    }
}
