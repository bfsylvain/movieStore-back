package com.moviestore.checkpoint.domain.director;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moviestore.checkpoint.domain.movie.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String photo;
    private Date birthday;
    private String country;
    private String biography;

    @OneToMany(mappedBy = "director", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnoreProperties("director")
    private List<Movie> movieList;

}
