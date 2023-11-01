package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailId(String email);
}
