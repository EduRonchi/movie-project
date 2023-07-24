package com.eduronchi.movieproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String actors;
    private String director;
    private String genre;
    private LocalDate releaseDate;
    private String description;

    @ManyToMany
    private List<Genre> genres;

}
