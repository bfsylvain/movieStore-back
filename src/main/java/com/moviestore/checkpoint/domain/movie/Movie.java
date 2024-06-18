package com.moviestore.checkpoint.domain.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moviestore.checkpoint.domain.director.Director;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String originalName;
    private String poster;
    private Integer releaseYear;
    private Integer length;
    private String pitch;
    private Boolean isSeen;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @JsonIgnoreProperties("movieList")
    private Director director;

}
