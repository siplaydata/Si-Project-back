package com.example.cocktail.Main.BookMark.service;

public interface BookMarkService {
    boolean checkUser(String nickName);

    String saveRecipe(Long id, String name);
}
