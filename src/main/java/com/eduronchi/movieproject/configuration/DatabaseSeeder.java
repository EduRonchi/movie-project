package com.eduronchi.movieproject.configuration;

import com.eduronchi.movieproject.entities.Genre;
import com.eduronchi.movieproject.entities.Movie;
import com.eduronchi.movieproject.repositories.GenreRepository;
import com.eduronchi.movieproject.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DatabaseSeeder {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public DatabaseSeeder(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            Genre dramaGenre = new Genre();
            dramaGenre.setName("Drama");
            genreRepository.save(dramaGenre);

            Genre comedyGenre = new Genre();
            comedyGenre.setName("Comedy");
            genreRepository.save(comedyGenre);

            Genre actionGenre = new Genre();
            actionGenre.setName("Action");
            genreRepository.save(actionGenre);

            Genre romanceGenre = new Genre();
            romanceGenre.setName("Romance");
            genreRepository.save(romanceGenre);

            Genre adventureGenre = new Genre();
            adventureGenre.setName("Adventure");
            genreRepository.save(adventureGenre);

            Movie loboDeWallStreet = new Movie();
            loboDeWallStreet.setName("O Lobo de Wall Street");
            loboDeWallStreet.setActors("Leonardo DiCaprio, Jonah Hill");
            loboDeWallStreet.setDirector("Martin Scorsese");
            loboDeWallStreet.setGenre("Drama");
            loboDeWallStreet.setReleaseDate(LocalDate.of(2013, 12, 25));
            loboDeWallStreet.setDescription("A true story of Jordan Belfort, from his rise to a wealthy stockbroker to his fall involving crime and corruption.");
            loboDeWallStreet.setGenres(Arrays.asList(dramaGenre));
            movieRepository.save(loboDeWallStreet);

            Movie titanic = new Movie();
            titanic.setName("Titanic");
            titanic.setActors("Leonardo DiCaprio, Kate Winslet");
            titanic.setDirector("James Cameron");
            titanic.setGenre("Drama");
            titanic.setReleaseDate(LocalDate.of(1997, 12, 19));
            titanic.setDescription("A fictionalized account of the sinking of the RMS Titanic.");
            titanic.setGenres(Arrays.asList(dramaGenre));
            movieRepository.save(titanic);

            Movie anjosDaLei = new Movie();
            anjosDaLei.setName("Anjos da Lei");
            anjosDaLei.setActors("Jonah Hill, Channing Tatum");
            anjosDaLei.setDirector("Phil Lord, Chris Miller");
            anjosDaLei.setGenre("Action");
            anjosDaLei.setReleaseDate(LocalDate.of(2012, 3, 16));
            anjosDaLei.setDescription("Two unlikely police officers go undercover as high school students.");
            anjosDaLei.setGenres(Arrays.asList(actionGenre));
            movieRepository.save(anjosDaLei);

            Movie oPoderosoChefao = new Movie();
            oPoderosoChefao.setName("O Poderoso Chefão");
            oPoderosoChefao.setActors("Marlon Brando, Al Pacino");
            oPoderosoChefao.setDirector("Francis Ford Coppola");
            oPoderosoChefao.setGenre("Drama");
            oPoderosoChefao.setReleaseDate(LocalDate.of(1972, 3, 14));
            oPoderosoChefao.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");
            oPoderosoChefao.setGenres(Arrays.asList(dramaGenre));
            movieRepository.save(oPoderosoChefao);

            Movie harryPotter = new Movie();
            harryPotter.setName("Harry Potter e a Pedra Filosofal");
            harryPotter.setActors("Daniel Radcliffe, Emma Watson");
            harryPotter.setDirector("Chris Columbus");
            harryPotter.setGenre("Adventure");
            harryPotter.setReleaseDate(LocalDate.of(2001, 11, 16));
            harryPotter.setDescription("Rescued from the outrageous neglect of his aunt and uncle, a young boy with a great destiny proves his worth while attending Hogwarts School of Witchcraft and Wizardry.");
            harryPotter.setGenres(Arrays.asList(adventureGenre));
            movieRepository.save(harryPotter);

            Movie piratasDoCaribe = new Movie();
            piratasDoCaribe.setName("Piratas do Caribe: A Maldição do Pérola Negra");
            piratasDoCaribe.setActors("Johnny Depp, Geoffrey Rush");
            piratasDoCaribe.setDirector("Gore Verbinski");
            piratasDoCaribe.setGenre("Adventure");
            piratasDoCaribe.setReleaseDate(LocalDate.of(2003, 7, 9));
            piratasDoCaribe.setDescription("Blacksmith Will Turner teams up with eccentric pirate Captain Jack Sparrow to save his love, the governor's daughter, from Jack's former pirate allies, who are now undead.");
            piratasDoCaribe.setGenres(Arrays.asList(adventureGenre));
            movieRepository.save(piratasDoCaribe);

        };
    }
}
