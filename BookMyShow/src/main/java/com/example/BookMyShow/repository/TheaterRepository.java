package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.Movie;
import com.example.BookMyShow.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {

}
