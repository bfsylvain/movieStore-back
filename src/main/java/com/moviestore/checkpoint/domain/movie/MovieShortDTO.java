package com.moviestore.checkpoint.domain.movie;

public record MovieShortDTO(
        Long id,
        String name,
        String poster,
        Integer releaseYear,
        Integer length,
        boolean isSeen
) {

    public static MovieShortDTO mapFromEntity(Movie movie) {
        return new MovieShortDTO(
                movie.getId(),
                movie.getName(),
                movie.getPoster(),
                movie.getReleaseYear(),
                movie.getLength(),
                movie.getIsSeen()
        );
    }

}
