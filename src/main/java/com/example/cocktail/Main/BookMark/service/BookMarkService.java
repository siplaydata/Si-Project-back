package com.example.cocktail.Main.BookMark.service;

import com.example.cocktail.Main.BookMark.dto.BookMarkDTO;

import java.util.List;

public interface BookMarkService {
    boolean checkUser(String nickName);

    void createBookMarkAndDelete(int id, String name, boolean type);

    List<BookMarkDTO> getBookMarkList(String nickName);
}
