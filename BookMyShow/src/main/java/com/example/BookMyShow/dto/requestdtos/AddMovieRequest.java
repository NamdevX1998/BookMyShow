package com.example.BookMyShow.dto.requestdtos;

import com.example.BookMyShow.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddMovieRequest {
    private String movieName;
    private Double rating;
    private Genre genre;
    private LocalDate releaseDate;
}

//for adding movie shows are not important.so thats why we not assign value to List<Show> showList.
