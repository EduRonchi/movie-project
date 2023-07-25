package com.eduronchi.movieproject.dto;

import com.eduronchi.movieproject.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long id;
    private String name;
    private String genre;
    private String description;

    public MovieDTO(Movie entity) {
        id = entity.getId();
        name = entity.getName();
        genre = entity.getGenre();
        description = entity.getDescription();
    }
}
