package com.moviestore.checkpoint.domain.movie;

import com.moviestore.checkpoint.domain.abstractService.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends AbstractService<Movie> {

    public MovieService(MovieRepository repository) {
        super (repository);
    }


}
