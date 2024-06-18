package com.moviestore.checkpoint.domain.movie;

import com.moviestore.checkpoint.util.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    Patcher patcher;


    @GetMapping("/get/all")
    public ResponseEntity<List<MovieShortDTO>> getAll() {
        try {
            List<Movie> movieList = movieService.getAll();
            List<MovieShortDTO> movieShortDTOList = movieList.stream().map(MovieShortDTO::mapFromEntity).toList();
            return new ResponseEntity<>(movieShortDTOList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/watched")
    public ResponseEntity<List<MovieShortDTO>> getWatched() {
        List<Movie> movieList = movieService.getAll();
        List<Movie> filteredMovieList = movieList.stream().filter(Movie::getIsSeen).toList();
        List<MovieShortDTO> movieShortDTOList = filteredMovieList.stream().map(MovieShortDTO::mapFromEntity).toList();
        return new ResponseEntity<>(movieShortDTOList, HttpStatus.OK);
    }

    @GetMapping("/get/not-watched")
    public ResponseEntity<List<MovieShortDTO>> getNotWatched() {
        List<Movie> movieList = movieService.getAll();
        List<Movie> filteredMovieList = movieList.stream().filter(movie -> !movie.getIsSeen()).toList();
        List<MovieShortDTO> movieShortDTOList = filteredMovieList.stream().map(MovieShortDTO::mapFromEntity).toList();
        return new ResponseEntity<>(movieShortDTOList, HttpStatus.OK);
    }


    @GetMapping("/get/{movieId}")
    public ResponseEntity<MovieDTO> getById(@PathVariable("movieId") Long movieId) {
        Movie foundMovie = movieService.getById(movieId);
        MovieDTO movieDTO = MovieDTO.mapFromEntity(foundMovie);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MovieShortDTO> add(@RequestBody Movie movie) {
        Movie createdMovie = movieService.add(movie);
        MovieShortDTO createdMovieDTO = MovieShortDTO.mapFromEntity(createdMovie);
        return new ResponseEntity<>(createdMovieDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{movieId}")
    public ResponseEntity<MovieShortDTO> updateMovie(@RequestBody Movie incompleteMovie, @PathVariable Long movieId) {
        Movie foundMovie = movieService.getById(movieId);

        try {
           patcher.elementPatcher(foundMovie, incompleteMovie);
           movieService.add(foundMovie);
        } catch(Exception e) {
            e.printStackTrace();
        }
        MovieShortDTO movieShortDTO = MovieShortDTO.mapFromEntity(foundMovie);
        return new ResponseEntity<>(movieShortDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable("movieId") Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
