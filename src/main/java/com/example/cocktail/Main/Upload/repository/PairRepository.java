package com.example.cocktail.Main.Upload.repository;

import com.example.cocktail.Main.Upload.model.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {
    List<Pair> findByInum(Long inum);
}
