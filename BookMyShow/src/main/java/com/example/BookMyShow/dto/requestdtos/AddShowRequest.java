package com.example.BookMyShow.dto.requestdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddShowRequest {
    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName; //Movie name is unique.
    private Integer theaterId;
}
