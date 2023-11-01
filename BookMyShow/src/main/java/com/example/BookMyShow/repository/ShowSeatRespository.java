package com.example.BookMyShow.repository;

import com.example.BookMyShow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRespository extends JpaRepository<ShowSeat,Integer> {
}
