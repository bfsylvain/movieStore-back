package com.moviestore.checkpoint.util;
import com.moviestore.checkpoint.domain.director.Director;
import com.moviestore.checkpoint.domain.director.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DirectorFixture {

    @Autowired
    DirectorService directorService;

    public void loadDirectors() throws ParseException {
        for (Director director : generateActor()) {
            directorService.add(director);
        }
}

    private List<Director> generateActor() throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date birthdayDirector1 = formatter.parse("1946-12-18");
        Date birthdayDirector2 = formatter.parse("1944-05-14");
        Date birthdayDirector3 = formatter.parse("1954-11-29");

        List<Director> directorList = new ArrayList<>();

        Director director1 = Director.builder()
                .firstname("Steven")
                .lastname("Spielberg")
                .photo("/assets/images/director-poster/Steven-Spielberg.jpg")
                .birthday(birthdayDirector1)
                .country("USA")
                .biography("Really famous, a lot of blockbusters of my childhood")
                .build();
        directorList.add(director1);

        Director director2 = Director.builder()
                .firstname("Georges")
                .lastname("Lucas")
                .photo("/assets/images/director-poster/George-Lucas.jpg")
                .birthday(birthdayDirector2)
                .country("USA")
                .biography("Born in the USA, blablabla.... Steven Spielberg's friend")
                .build();
        directorList.add(director2);

        Director director4 = Director.builder()
                .firstname("Joel")
                .lastname("Cohen")
                .photo("/assets/images/director-poster/Joel-Cohen.jpg")
                .birthday(birthdayDirector3)
                .biography("A lot of amazing movies with his brother, the duuude man !")
                .country("USA")
                .build();
        directorList.add(director4);

        return directorList;
    }
}