package com.eduronchi.movieproject.services;

import com.eduronchi.movieproject.dto.MovieDTO;
import com.eduronchi.movieproject.entities.Movie;
import com.eduronchi.movieproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private  MovieRepository movieRepository;

    public List<MovieDTO> findAll() {
        List<Movie> result = movieRepository.findAll();
        return result.stream().map(MovieDTO::new).toList();
    }

    public List<MovieDTO> findByGenre(String genreName) {
        List<Movie> result = movieRepository.findByGenre(genreName);
        return result.stream().map(MovieDTO::new).toList();
    }

    public List<Movie> searchMoviesByExample(Example<Movie> example) {
        return movieRepository.findAll(example);
    }

    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }

}
