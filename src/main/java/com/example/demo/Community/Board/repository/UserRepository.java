package com.example.demo.Community.Board.repository;

import com.example.demo.Community.Board.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByNickname(String nickname);
}
