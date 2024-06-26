package com.saveetha.bookplix.movie;
import com.saveetha.bookplix.movie.MovieService;
import com.saveetha.bookplix.movie.ShowService;
import com.saveetha.bookplix.movie.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MovieConfig {
    @Autowired
    private ShowService showService;

    @Autowired
    public MovieService movieService;

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository repository){
        return args -> {
            Movie logan = new Movie(
                    1L,
                    "Logan",
                    "Sci-Fi",
                    10F
            );
            Movie tangled = new Movie(
                    2L,
                    "Tangled",
                    "Fantasy",
                    9.4F
            );
            Movie deadPool = new Movie(
                    3L,
                    "DeadPool",
                    "Sci-Fi, Comedy",
                    9F
            );
            repository.saveAll(List.of(logan, tangled, deadPool));

            List<Long> movieIdList = movieService.getMovieIds();
            for (Long movieId : movieIdList) {
                showService.createShowsForMovie(movieId);
            }
        };
    }
}
