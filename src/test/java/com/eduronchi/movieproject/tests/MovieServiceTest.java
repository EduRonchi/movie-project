package com.eduronchi.movieproject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.eduronchi.movieproject.dto.MovieDTO;
import com.eduronchi.movieproject.entities.Movie;
import com.eduronchi.movieproject.repositories.MovieRepository;
import com.eduronchi.movieproject.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        movieRepository = mock(MovieRepository.class);
        movieService = new MovieService(movieRepository);
    }

    @Test
    public void testFindAll() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie 1", "Action", "Description 1"));
        movies.add(new Movie(2L, "Movie 2", "Drama", "Description 2"));

        List<MovieDTO> movieDTOs = movies.stream().map(MovieDTO::new).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(0, 2); // Page 0, Size 2
        Page<Movie> pageResult = new PageImpl<>(movies, pageRequest, movies.size());

        when(movieRepository.findAll(pageRequest)).thenReturn(pageResult);

        Page<MovieDTO> result = movieService.findAll(0, 2);

        assertEquals(movieDTOs, result.getContent());

        verify(movieRepository, times(1)).findAll(pageRequest);
    }

    @Test
    public void testFindByGenre() {
        String genreName = "ação";
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie 1", "Ação", "Description 1"));
        movies.add(new Movie(2L, "Movie 2", "Ação", "Description 2"));

        List<MovieDTO> movieDTOs = movies.stream().map(MovieDTO::new).collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(0, 2); // Page 0, Size 2
        Page<Movie> pageResult = new PageImpl<>(movies, pageRequest, movies.size());

        when(movieRepository.findByGenre("Ação", pageRequest)).thenReturn(pageResult);

        Page<MovieDTO> result = movieService.findByGenre(genreName, 0, 2);

        assertEquals(movieDTOs, result.getContent());

        verify(movieRepository, times(1)).findByGenre("Ação", pageRequest);
    }

    @Test
    public void testSearchMoviesByExample() {
        Movie movieExample = new Movie();
        movieExample.setGenre("Ação");

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1L, "Movie 1", "Ação", "Description 1"));
        movies.add(new Movie(2L, "Movie 2", "Ação", "Description 2"));

        PageRequest pageRequest = PageRequest.of(0, 2); // Page 0, Size 2
        Page<Movie> pageResult = new PageImpl<>(movies, pageRequest, movies.size());

        when(movieRepository.findAll(Example.of(movieExample), pageRequest)).thenReturn(pageResult);

        Page<Movie> result = movieService.searchMoviesByExample(Example.of(movieExample), 0, 2);

        assertEquals(pageResult, result);

        verify(movieRepository, times(1)).findAll(Example.of(movieExample), pageRequest);
    }

    @Test
    public void testSaveMovie() {
        Movie movieToSave = new Movie();
        movieToSave.setName("Filme de Teste");
        movieToSave.setGenre("Ação");
        movieToSave.setDescription("Um filme de teste para o método save");

        when(movieRepository.save(movieToSave)).thenReturn(movieToSave);

        Movie savedMovie = movieService.save(movieToSave);

        assertEquals(movieToSave, savedMovie);

        verify(movieRepository, times(1)).save(movieToSave);
    }

    @Test
    public void testFindById() {
        Movie existingMovie = new Movie();
        existingMovie.setId(1L);
        existingMovie.setName("Filme Existente");
        existingMovie.setGenre("Ação");
        existingMovie.setDescription("Um filme já existente para teste do método findById");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(existingMovie));

        Optional<Movie> foundMovie = movieService.findById(1L);

        assertEquals(existingMovie, foundMovie.get());

        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteById() {
        Long movieId = 1L;

        movieService.deleteById(movieId);

        verify(movieRepository, times(1)).deleteById(movieId);
    }
}
