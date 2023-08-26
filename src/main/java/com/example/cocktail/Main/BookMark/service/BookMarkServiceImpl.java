package com.example.cocktail.Main.BookMark.service;

import com.example.cocktail.Main.BookMark.repository.BookMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    private BookMarkRepository bookMarkRepository;
    @Override
    public boolean checkUser(String nickName) {
        return bookMarkRepository.findByNickName(nickName) != null;
    }
    @Override
    public String saveRecipe(Long id, String name) {
        return null;
    }
}
