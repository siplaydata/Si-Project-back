package com.example.cocktail.Main.TextChoice.repository;
import com.example.cocktail.Main.TextChoice.model.TextChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextChoiceRepository extends JpaRepository<TextChoice, Integer> {
}
