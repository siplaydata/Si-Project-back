package com.example.cocktail.Main.UploadPost.repository;

import com.example.cocktail.Main.UploadPost.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
    Images findByCnum(Long cnum);
}
