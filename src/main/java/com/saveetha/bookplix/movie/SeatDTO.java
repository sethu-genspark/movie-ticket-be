package com.saveetha.bookplix.movie;

public class SeatDTO {
    private Long id;
    private Boolean isBooked;
    private int price;
    public SeatDTO(Long id, Boolean isBooked, int price) {
        this.id = id;
        this.isBooked = isBooked;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }
}
