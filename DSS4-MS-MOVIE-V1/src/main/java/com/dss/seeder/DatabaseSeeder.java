/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.seeder;

import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.util.enums.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class is a Database Seeder for DSS web app
 */

@Component
public class DatabaseSeeder {

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){

        List<DssMovie> dssMovieList = dssMovieRepository.findAll();
        if(dssMovieList.isEmpty()){
            DssMovie movie = new DssMovie(
                    "DSS0001",
                    "The Avengers",
                    "2021",
                    "Stan Lee",
                    "Joss Whedon",
                    "Trinh Tran",
                    "Alan Silvestri",
                    "143 minutes",
                    400000000,
                    "PG-13 TV-14",
                    "United States",
                    "English",
                    new Date(),
                    UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                    null,
                    null
            );
            dssMovieRepository.save(movie);

            List<Reviews> reviewsList = Arrays.asList(
                    new Reviews("RT0001", "DSS0001", 5, "Rating Headline 1", "Rating Content 1", new Date(),  UserRoles.ROLE_SUPER_ADMIN.getStrRole(), null, null, movie),
                    new Reviews("RT0002", "DSS0001", 5, "Rating Headline 2", "Rating Content 2", new Date(),  UserRoles.ROLE_SUPER_ADMIN.getStrRole(), null, null, movie)
            );
            reviewsRepository.saveAll(reviewsList);

            List<Actors> actorsList = Arrays.asList(
                    new Actors("AC0001", "DSS0001", "Robert", "Downey Jr.", "Male", 45, "Tony Stark/Iron Man", UserRoles.ROLE_SUPER_ADMIN.getStrRole(), new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(), null, null, movie),
                    new Actors("AC0002", "DSS0001", "Chris", "Evans", "Male", 30, "Captain America", UserRoles.ROLE_SUPER_ADMIN.getStrRole(), new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(), null, null, movie)
            );
            actorsRepository.saveAll(actorsList);
        }
    }

}
