package com.eduronchi.movieproject.controller;

import com.eduronchi.movieproject.dto.MovieDTO;
import com.eduronchi.movieproject.entities.Movie;
import com.eduronchi.movieproject.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/genre/{genreName}")
    public List<MovieDTO> findByGenre(@PathVariable String genreName) {
        String genreNameCapitalized = capitalizeFirstLetter(genreName);
        return movieService.findByGenre(genreNameCapitalized);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Movie> findById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie save(@RequestBody Movie Movie) {
        return movieService.save(Movie);
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
        movieService.deleteById(id);
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
    }
}
