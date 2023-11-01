package com.example.BookMyShow.dto.requestdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookTicketRequest {
    private String movieName;

    private Integer theaterId;

    private LocalDate showDate;

    private LocalTime showTime;

    private List<String> requestedSeatNos;

    private Integer userId;
}
