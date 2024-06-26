//package com.ticketBooking.movieTicketBooking.movie;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class StartUpRunner implements CommandLineRunner {
//    @Autowired
//    private ShowService showService;
//    @Autowired
//    private MovieService movieService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        createShowsForExistingMovies();
//    }
//
//    private void createShowsForExistingMovies() {
//        System.out.println("helooooooooooooooooooooooooo");
//        List<Long> movieIdList = movieService.getMovieIds();
//        for (Long movieId : movieIdList) {
//            showService.createShowsForMovie(movieId);
//        }
//    }
//}
