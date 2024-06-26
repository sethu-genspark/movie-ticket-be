package com.saveetha.bookplix.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    @Autowired
    private ShowRespository showRespository;


    public List<ShowDTO> getShowByMovieId(Long id){
        List<Show> shows = showRespository.findByMovieId((id));
        return shows.stream()
                .map(show -> new ShowDTO(show.getId(), show.getShowTime()))
                .collect(Collectors.toList());
    }

    @Autowired
    private MovieRepository movieRepository;

    public void createShowsForMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));
        List<Show> shows = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Show show = new Show();
            show.setMovie(movie);
            show.setShowTime(i * 3);
            show.setSeats(createSeatsForShow(show));
            shows.add(show);
        }

        showRespository.saveAll(shows);
    }

    private List<Seats> createSeatsForShow(Show show) {
        List<Seats> seats = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Seats seat = new Seats();
            if (i <= 5){
                seat.setPrice(250);
            }
            else if (i <= 10) {
                seat.setPrice(200);
            }
            else {
                seat.setPrice(100);
            }
            seat.setBooked(false);
            seat.setShow(show);
            seats.add(seat);
        }
        return seats;
    }

}
