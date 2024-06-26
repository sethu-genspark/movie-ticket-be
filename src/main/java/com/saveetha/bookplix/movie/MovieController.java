package com.saveetha.bookplix.movie;
import com.saveetha.bookplix.movie.MovieService;
import com.saveetha.bookplix.movie.ShowService;
import com.saveetha.bookplix.movie.SeatService;
import com.saveetha.bookplix.movie.MovieRepository;
import com.saveetha.bookplix.movie.SeatRepository;
import com.saveetha.bookplix.movie.ShowDTO;
import com.saveetha.bookplix.movie.MovieDTO;
import com.saveetha.bookplix.movie.SeatDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/movieList")
public class MovieController {

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @Autowired
    private ShowService showService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private  MovieRepository movieRepository;

    @Autowired
    private SeatRepository seatRepository;

    @GetMapping
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieDetails(@PathVariable Long movieId){
        Movie movie = movieService.getByMovieId(movieId);
        List<ShowDTO> shows = showService.getShowByMovieId(movieId);
        Map<String, Object> response = new HashMap<>();
        response.put("movie", movie);
        response.put("shows", shows);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}/{showId}")
    public ResponseEntity<?> getShowSeats(
            @PathVariable Long movieId,
            @PathVariable Long showId){

        movieService.checkShowInMovie(movieId, showId);
        Movie movie = movieService.getByMovieId(movieId);
        List<SeatDTO> seats = seatService.getSeatsByShowId(showId);
        Map<String, Object> response = new HashMap<>();
        response.put("movie", movie);
        response.put("seats", seats);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{movieId}/{showId}/details")
    public ResponseEntity<?> getBookedDetails(@PathVariable Long movieId,
                                              @PathVariable Long showId){

        movieService.checkShowInMovie(movieId, showId);
        List<Seats> seats = seatRepository.findByShowId(showId);
        List<Long> bookedSeatId = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        int count = 0;
        int priceTotal = 0;
        for(Seats seat :seats){
            if(seat.getBooked()){
                bookedSeatId.add(seat.getId());
                count +=1;
                priceTotal += seat.getPrice();
            }
        }
        response.put("No of seats booked", count);
        response.put("seats", bookedSeatId);
        response.put("Price", priceTotal);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{movieId}/{showId}")
    public void bookTicketForShow(@PathVariable Long movieId,
                                  @PathVariable Long showId,
                                  @RequestBody List<SeatDTO> seatsIdList){
        seatService.checkSeatExist(showId, seatsIdList);
        movieService.checkShowInMovie(movieId, showId);
        seatService.setSeatBooked(showId, seatsIdList);
    }
    @PostMapping("addMovie")
    public void addNewMovie(@RequestBody MovieDTO movieDTO){
        movieService.checkMovieExist(movieDTO.getName());
        Movie movie = new Movie();
        movie.setName(movieDTO.getName());
        movie.setGenre(movieDTO.getGenre());
        movie.setRating(movieDTO.getRating());
        movieRepository.save(movie);
        showService.createShowsForMovie(movie.getId());
    }

}
