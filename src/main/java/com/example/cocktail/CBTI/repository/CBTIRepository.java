package com.example.cocktail.CBTI.repository;

import com.example.cocktail.CBTI.model.CBTI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CBTIRepository extends JpaRepository<CBTI, Integer> {
    List<CBTI> findAllByLevelBetweenAndTasteAndPlace(int minLevel, int maxLevel, String taste, String place);
}
