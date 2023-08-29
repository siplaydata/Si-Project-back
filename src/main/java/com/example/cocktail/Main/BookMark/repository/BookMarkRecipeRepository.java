package com.example.cocktail.Main.BookMark.repository;

import com.example.cocktail.Commnity.Userinfo.model.User;
import com.example.cocktail.Main.BookMark.model.BookMarkRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMarkRecipeRepository extends JpaRepository<BookMarkRecipe, Integer> {
}
