package com.example.cocktail.Main.BookMark.service;

import com.example.cocktail.Main.BookMark.dto.BookMarkDTO;
import com.example.cocktail.Main.BookMark.model.BookMarkRecipe;
import com.example.cocktail.Main.BookMark.model.BookMarkUser;
import com.example.cocktail.Main.BookMark.model.BookMark;
import com.example.cocktail.Main.BookMark.repository.BookMarkRecipeRepository;
import com.example.cocktail.Main.BookMark.repository.BookMarkRepository;
import com.example.cocktail.Main.BookMark.repository.BookMarkUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Autowired
    private BookMarkRepository bookMarkRepository;
    @Autowired
    private BookMarkUserRepository bookMarkUserRepository;
    @Autowired
    private BookMarkRecipeRepository bookMarkRecipeRepository;
    public boolean checkUser(String nickName) {
        return bookMarkUserRepository.findByNickname(nickName) != null;
    }
    private BookMarkUser findByUserByNickname(String nickName){
        return bookMarkUserRepository.findByNickname(nickName);
    }
    private BookMarkRecipe findByRecipeById(int id) {
        return bookMarkRecipeRepository.findById(id).orElse(null);
    }
    public void createBookMarkAndDelete(int id, String nickName, boolean type) {
        System.out.println(id);
        System.out.println(nickName);
        BookMarkUser userinfo = findByUserByNickname(nickName);
        BookMarkRecipe recipe = findByRecipeById(id);

        if (userinfo != null && recipe != null) {
            try {
                if (type) {
                    BookMark bookMarkRecipe = new BookMark();
                    bookMarkRecipe.setBookMarkUser(userinfo);
                    bookMarkRecipe.setBookMarkRecipe(recipe);
                    bookMarkRepository.save(bookMarkRecipe);
                } else {
                    BookMark existingBookMark = bookMarkRepository.findByBookMarkUserAndBookMarkRecipe(userinfo, recipe);
                    if (existingBookMark != null) {
                        bookMarkRepository.delete(existingBookMark);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("서버 장애 발생 : " + e.getMessage());
            }
        }
    }
    public List<BookMarkDTO> getBookMarkList(String nickName) {
        BookMarkUser bookMarkUser = findByUserByNickname(nickName);

        if (bookMarkUser != null) {
            try {
                List<BookMark> bookmarks = bookMarkRepository.findByBookMarkUser(bookMarkUser);

                List<BookMarkDTO> bookMarkDTOs = new ArrayList<>();
                for (BookMark bookmark : bookmarks) {
                    BookMarkRecipe recipe = bookmark.getBookMarkRecipe();
                    BookMarkDTO bookMarkDTO = new BookMarkDTO();
                    bookMarkDTO.setId(recipe.getId());
                    bookMarkDTO.setKoreanCocktailName(recipe.getCocktail().getKoreanName());
                    bookMarkDTO.setEnglishCocktailName(recipe.getCocktail().getEnglishName());
                    bookMarkDTO.setIngredients(recipe.getIngredients());
                    bookMarkDTO.setMethod(recipe.getMethod());
                    bookMarkDTO.setGarnish(recipe.getGarnish());
                    bookMarkDTO.setImages(recipe.getImages().getPicture());

                    bookMarkDTOs.add(bookMarkDTO);
                }

                return bookMarkDTOs;
            } catch (Exception e) {
                throw new RuntimeException("서버 장애 발생 : " + e.getMessage());
            }
        }
        throw new RuntimeException("알 수 없는 오류");
    }
}
