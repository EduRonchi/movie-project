package com.eduronchi.movieproject.repositories;

import com.eduronchi.movieproject.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByGenre(String genre, Pageable pageable);
}
