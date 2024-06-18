package com.moviestore.checkpoint.domain.movie;

import com.moviestore.checkpoint.domain.director.DirectorBasicDTO;

public record MovieDTO(
        Long id,
        String name,
        String originalName,
        String poster,
        Integer releaseYear,
        Integer length,
        String pitch,
        Boolean isSeen,
        DirectorBasicDTO director,
        String comment

) {
    public static MovieDTO mapFromEntity(Movie movie) {

        return new MovieDTO(
                movie.getId(),
                movie.getName(),
                movie.getOriginalName(),
                movie.getPoster(),
                movie.getReleaseYear(),
                movie.getLength(),
                movie.getPitch(),
                movie.getIsSeen(),
                movie.getDirector() != null ? DirectorBasicDTO.mapFromEntity(movie.getDirector()) : null,
                movie.getComment()
        );
    }
}
