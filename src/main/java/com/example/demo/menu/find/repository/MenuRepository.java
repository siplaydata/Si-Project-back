package com.example.demo.menu.find.repository;

import com.example.demo.menu.find.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Info, String> {

}
