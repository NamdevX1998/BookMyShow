package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.requestdtos.AddMovieRequest;
import com.example.BookMyShow.model.Movie;
import com.example.BookMyShow.repository.MovieRepository;
import com.example.BookMyShow.trasnformer.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(AddMovieRequest movieRequest)throws Exception {
        Movie movie= MovieTransformer.movieRequestToMovie(movieRequest);
        movieRepository.save(movie);//this line throw error if we try to save movie in db which is already presented
        //because we define movie name as unique.
        return "Movie has been added successfully in database";

    }
}
