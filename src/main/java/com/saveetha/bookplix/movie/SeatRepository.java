package com.saveetha.bookplix.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seats, Long> {
    List<Seats> findByShowId(Long showId);
//    Seats findBySeatId(Long seatId);
}
