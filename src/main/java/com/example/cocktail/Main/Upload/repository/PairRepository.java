package com.example.cocktail.Main.UploadPost.repository;

import com.example.cocktail.Main.UploadPost.model.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {
    List<Pair> findByInum(Long inum);
}
