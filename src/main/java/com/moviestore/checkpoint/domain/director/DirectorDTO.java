package com.moviestore.checkpoint.domain.director;

import com.moviestore.checkpoint.domain.movie.MovieShortDTO;

import java.util.Date;
import java.util.List;

public record DirectorDTO(
        Long id,
        String firstname,
        String lastname,
        String photo,
        Date birthday,
        String country,
        String biography,
        List<MovieShortDTO> movieShortDTOList
) {
    public static DirectorDTO mapFromEntity(Director director) {
        List<MovieShortDTO> movieShortDTOS = director.getMovieList().stream().map(MovieShortDTO::mapFromEntity).toList();
        return new DirectorDTO(
            director.getId(),
            director.getFirstname(),
            director.getLastname(),
            director.getPhoto(),
            director.getBirthday(),
            director.getCountry(),
            director.getBiography(),
            movieShortDTOS
        );
    }
}
