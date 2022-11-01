package com.dss.seeder;

import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.repository.roles.RolesRepository;
import com.dss.repository.user.UsersRepository;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Component
public class DatabaseSeeder {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){
        List<Users> users = usersRepository.findAll();
        if(users.isEmpty()){
            Users user = new Users(
                    "US0001",
                    "Glenn Mark",
                    "Anduiza",
                    "glenmark.ghl@gmail.com",
                    encoder.encode("P@$$w0rd1234"),
                    encoder.encode("P@$$w0rd1234"),
                    UserStatus.ACTIVE.toString(),
                    "09106121529",
                    new Date(),
                    UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                    null,
                    null
            );
            usersRepository.save(user);

            List<Roles> roleList = Arrays.asList(
                    new Roles("RS1", UserRoles.ROLE_SUPER_ADMIN.getStrRole(), user),
                    new Roles("RS2", UserRoles.ROLE_ADMIN.getStrRole(), user));
            rolesRepository.saveAll(roleList);
        }

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
                    "the-avengers.jpg",
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
