package com.eduronchi.movieproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Name field is required.")
    private String name;
    private String actors;
    private String director;

    @NotBlank(message = "Genre field is required.")
    private String genre;
    private LocalDate releaseDate;

    @NotBlank(message = "Description field is required.")
    private String description;

    @ManyToMany
    @JsonIgnore
    private List<Genre> genres;

}
