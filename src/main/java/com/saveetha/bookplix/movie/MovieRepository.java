package com.saveetha.bookplix.movie;

import com.saveetha.bookplix.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByName(String name);
}
