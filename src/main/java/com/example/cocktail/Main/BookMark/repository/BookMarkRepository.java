package com.example.cocktail.Main.BookMark.repository;

import com.example.cocktail.Main.BookMark.model.BookMarkRecipe;
import com.example.cocktail.Main.BookMark.model.BookMarkUser;
import com.example.cocktail.Main.BookMark.model.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Integer> {
    BookMark findByBookMarkUserAndBookMarkRecipe(BookMarkUser userinfo, BookMarkRecipe recipe);

    List<BookMark> findByBookMarkUser(BookMarkUser bookMarkUser);
}
