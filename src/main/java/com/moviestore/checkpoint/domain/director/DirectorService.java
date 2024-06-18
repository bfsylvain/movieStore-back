package com.moviestore.checkpoint.domain.director;

import com.moviestore.checkpoint.domain.abstractService.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class DirectorService extends AbstractService<Director> {

    public DirectorService(DirectorRepository repository) {
        super(repository);
    }
}
