package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.Movie;
import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
    Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate date, LocalTime time, Movie movie, Theater theater);
}
