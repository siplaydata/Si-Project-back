package com.example.demo.User.LogIn.repository;

import com.example.demo.User.LogIn.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoginRepository extends JpaRepository<LoginUser, String> {
    LoginUser findByUsername(String username);
}
