package com.example.cocktail.User.Join.repository;

import com.example.cocktail.User.Join.model.JoinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<JoinUser, Long> {
    JoinUser findByUsername(String username);
    JoinUser findByNickname(String nickname);
}
