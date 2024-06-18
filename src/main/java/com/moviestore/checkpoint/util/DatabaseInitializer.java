package com.moviestore.checkpoint.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final DirectorFixture directorFixture;
    private final MovieFixture movieFixture;

    @Override
    public void run(String... args) throws Exception {
        directorFixture.loadDirectors();
        movieFixture.loadMovies();
    }


}
