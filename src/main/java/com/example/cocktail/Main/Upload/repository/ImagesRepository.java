package com.example.cocktail.Main.Upload.repository;

import com.example.cocktail.Main.Upload.model.Cocktail;
import com.example.cocktail.Main.Upload.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer> {
    Images findById(int id);
}
