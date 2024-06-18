package com.moviestore.checkpoint.domain.director;

public record DirectorBasicDTO(
        Long id,
        String firstname,
        String lastname,
        String photo
) {
    public static DirectorBasicDTO mapFromEntity(Director director) {
        return new DirectorBasicDTO(
                director.getId(),
                director.getFirstname(),
                director.getLastname(),
                director.getPhoto()
        );
    }
}
