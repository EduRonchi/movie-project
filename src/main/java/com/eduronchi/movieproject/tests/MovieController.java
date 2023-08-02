package com.eduronchi.movieproject.tests;

import com.eduronchi.movieproject.dto.MovieDTO;
import com.eduronchi.movieproject.entities.Movie;
import com.eduronchi.movieproject.exceptions.ResourceNotFoundException;
import com.eduronchi.movieproject.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public Page<MovieDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return movieService.findAll(page, size);
    }

    @GetMapping("/genre/{genreName}")
    public Page<MovieDTO> findByGenre(
            @PathVariable String genreName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        String genreNameCapitalized = capitalizeFirstLetter(genreName);
        return movieService.findByGenre(genreNameCapitalized, page, size);
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        return getMovieById(id);
    }

    @GetMapping("/search")
    public Page<Movie> searchMoviesByExample(Movie filter,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size
    ) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Movie> example = Example.of(filter, matcher);
        Page<Movie> moviePage = movieService.searchMoviesByExample(example, page, size);
        return moviePage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie save(@Valid @RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Movie update (@PathVariable Long id, @RequestBody Movie update) {
        update.setId(id);
        return movieService.save(update);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        Movie movie = getMovieById(id);
        movieService.deleteById(id);
    }

    private Movie getMovieById(Long id) {
        Optional<Movie> movie = movieService.findById(id);
        return movie.orElseThrow(() -> new ResourceNotFoundException("Movie not found with ID: " + id));
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
    }
}
