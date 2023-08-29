package com.example.cocktail.Main.BookMark.repository;

import com.example.cocktail.Main.BookMark.model.BookMarkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkUserRepository extends JpaRepository<BookMarkUser, Integer> {
    BookMarkUser findByNickname(String nickName);
}
