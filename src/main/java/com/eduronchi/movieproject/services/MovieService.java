package com.eduronchi.movieproject.services;

import com.eduronchi.movieproject.dto.MovieDTO;
import com.eduronchi.movieproject.entities.Movie;
import com.eduronchi.movieproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieDTO> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Movie> result = movieRepository.findAll(pageRequest);
        return result.map(MovieDTO::new);
    }

    public Page<MovieDTO> findByGenre(String genreName, int page, int size) {
        String genreNameCapitalized = capitalizeFirstLetter(genreName);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Movie> result = movieRepository.findByGenre(genreNameCapitalized, pageRequest);
        return result.map(MovieDTO::new);
    }

    public Page<Movie> searchMoviesByExample(Example<Movie> example, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return movieRepository.findAll(example, pageRequest);
    }

    public Movie save(Movie movie) {
        if (movieRepository.existsByName(movie.getName())) {
            throw new IllegalArgumentException("A movie with the same name already exists.");
        }

        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
    }
}
