package com.example.cocktail.User.Info.repository;

import com.example.cocktail.User.Info.dto.InfoDTO;
import com.example.cocktail.User.Info.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer> {
    Info findByNickname(String nickName);
}
