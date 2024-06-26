package com.saveetha.bookplix.movie;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )

    private Long id;
    private String name;
    private String genre;
    private Float rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show> shows;



    public Movie() {
    }

    public Movie(Long id, String name, String genre, Float rating) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }

    public Movie(String name, String genre, Float rating) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

//    public List<Show> getShows() {
//        return shows;
//    }
//
//    public void setShows(List<Show> shows) {
//        this.shows = shows;
//    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}';
    }
}
