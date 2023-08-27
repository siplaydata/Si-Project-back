package com.example.cocktail.Main.BookMark.repository;

import com.example.cocktail.Main.BookMark.model.BookMarkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookMarkRepository extends JpaRepository<BookMarkUser, Integer> {
    BookMarkUser findByNickName(String nickName);
}
