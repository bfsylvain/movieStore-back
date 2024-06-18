package com.moviestore.checkpoint.util;

import com.moviestore.checkpoint.domain.director.Director;
import com.moviestore.checkpoint.domain.director.DirectorService;
import com.moviestore.checkpoint.domain.movie.Movie;
import com.moviestore.checkpoint.domain.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieFixture {

    @Autowired
    MovieService movieService;

    @Autowired
     private DirectorService directorService;

    public void loadMovies() throws ParseException {
        for (Movie movie: generateMovie()) {
            movieService.add(movie);
        }
    }

    private List<Movie> generateMovie() throws ParseException {

        Director director1 = directorService.getById(1L);
        Director director2 = directorService.getById(2L);
        Director director3 = directorService.getById(3L);

        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = Movie.builder()
                .name("indiana Jones : L'arche perdue")
                .originalName("Indiana Jones : The lost ark")
                .poster("/assets/images/movie-poster/IndianaJones.jpg")
                .director(director1)
                .releaseYear(1981)
                .length(115)
                .pitch("Archeologist with a whip")
                .isSeen(true)
                .comment("Ta, tadadata, ta, tadadata, ta tada-ta, ta tada")
                .build();
        movieList.add(movie1);

        Movie movie2 = Movie.builder()
                .name("E.T., l'extraterrestre")
                .originalName("E.T., the Extra-terrestrial")
                .poster("/assets/images/movie-poster/ET.jpg")
                .director(director1)
                .releaseYear(1982)
                .length(110)
                .pitch("weird thing on earth")
                .isSeen(false)
                .build();
        movieList.add(movie2);

        Movie movie3 = Movie.builder()
                .name("Star Wars : Épisode IV-Un nouvel espoir")
                .originalName("Star Wars: Episode IV-A new hope")
                .poster("/assets/images/movie-poster/StarWarsIV.jpg")
                .director(director2)
                .releaseYear(1977)
                .length(121)
                .pitch("space cowboys & nazis")
                .isSeen(true)
                .comment("Top 3 of the best movie ever, a classic of my childhood on VHS")
                .build();
        movieList.add(movie3);

        Movie movie4 = Movie.builder()
                .name("The big Lebowski")
                .originalName("The big Lebowski")
                .poster("/assets/images/movie-poster/Lebowski.jpg")
                .director(director3)
                .releaseYear(1998)
                .length(117)
                .pitch("The dude, bowling and weed")
                .isSeen(true)
                .comment("My favourite movie of all time, cause, he's the dude man, and nobody pees on the dude' rug...")
                .build();
        movieList.add(movie4);

        Movie movie5 = Movie.builder()
                .name("Les dents de la mer")
                .originalName("Jaws")
                .poster("/assets/images/movie-poster/Jaws.jpg")
                .director(director1)
                .releaseYear(1975)
                .length(124)
                .pitch("hungry fish")
                .isSeen(false)
                .build();
        movieList.add(movie5);


        return movieList;
    }
}
