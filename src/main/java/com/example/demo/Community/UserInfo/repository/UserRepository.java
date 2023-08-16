package com.example.demo.Community.UserInfo.repository;

import com.example.demo.Community.UserInfo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}
