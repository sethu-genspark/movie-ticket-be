package com.saveetha.bookplix.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private  SeatRepository seatRepository;

    public List<SeatDTO> getSeatsByShowId(Long id){
        List<Seats> seats = seatRepository.findByShowId(id);
        List<Seats> freeSeats = new ArrayList<>();
        for(Seats seat:seats){
            if (!seat.getBooked()){
                freeSeats.add(seat);
            }
        }
        return freeSeats.stream()
                .map(seats1 -> new SeatDTO(seats1.getId(), seats1.getBooked(), seats1.getPrice()))
                .collect(Collectors.toList());
    }

    public Seats getSeatById(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat not found with ID: " + seatId));
    }

    public void setSeatBooked(Long showId, List<SeatDTO> seatIdList) {

        for (SeatDTO seatId : seatIdList) {
            Long id = seatId.getId();
            if (id > showId*15 || id <= (showId-1)*15 ){
                throw new IllegalStateException("No seats found");
            }
            Boolean check = seatId.getBooked();
            Seats seat = getSeatById(id);
            seat.setBooked(check);
            seat.setId(id);
            seatRepository.save(seat);
        }

    }
    public void checkSeatExist(Long showId,List<SeatDTO> seatsIdList) {
        for(SeatDTO seatDTO:seatsIdList){
            if(getSeatById(seatDTO.getId()).getBooked()){
                throw new IllegalStateException("Seats Already Booked");
            }
        }
    }
}
