package com.example.demo.log.Join.repository;

import com.example.demo.log.Join.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userid);
    User save(User user);
}
