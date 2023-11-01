package com.example.BookMyShow.dto.responsedtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowResponse {
    String movieName;
    LocalDate show_date;
    LocalTime show_time;
    String theaterName;
}
