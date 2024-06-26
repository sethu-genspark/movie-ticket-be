package com.saveetha.bookplix.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Autowired
    private ShowRespository showRespository;

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public List<Long> getMovieIds(){
        List<Movie> movies = movieRepository.findAll();
        List<Long> idList = new ArrayList<>();
        for (Movie movie:movies){
            idList.add(movie.getId());
        }
        return idList;
    }

    public Movie getByMovieId(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public void checkShowInMovie(Long movieId, Long showId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));

        if ( (showId > movieId*5) || showId <= (movieId-1) * 5){
            throw new IllegalStateException("Show not found");
        }
    }

    public void checkMovieExist(String name) {
        if (movieRepository.findByName(name) != null){
            throw new IllegalStateException("Movie already exist");
        }
    }
}
