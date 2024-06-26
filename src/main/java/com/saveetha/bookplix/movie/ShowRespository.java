package com.saveetha.bookplix.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowRespository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Long movieId);
}
