package com.saveetha.bookplix.movie;

public class ShowDTO {
    private Long id;
    private int showTime;

    public ShowDTO(Long id, int showTime) {
        this.id = id;
        this.showTime = showTime;
    }

    public int getShowTime() {
        return showTime;
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
