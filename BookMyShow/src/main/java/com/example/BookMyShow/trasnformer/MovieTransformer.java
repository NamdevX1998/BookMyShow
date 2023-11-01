package com.example.BookMyShow.trasnformer;

import com.example.BookMyShow.dto.requestdtos.AddMovieRequest;
import com.example.BookMyShow.model.Movie;

public class MovieTransformer {
    public static Movie movieRequestToMovie(AddMovieRequest movieRequest){
        return Movie.builder()
                .movieName(movieRequest.getMovieName())
                .genre(movieRequest.getGenre())
                .releaseDate(movieRequest.getReleaseDate())
                .rating(movieRequest.getRating())
                .build();

    }
}
