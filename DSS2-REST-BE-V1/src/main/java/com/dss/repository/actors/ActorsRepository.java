package com.dss.repository.actors;

import com.dss.entity.actors.Actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@Repository
public interface ActorsRepository  extends JpaRepository<Actors, String> {

    @Query(value = "SELECT * FROM DSS_ACTORS WHERE FIRST_NAME LIKE '%'||:firstName||'%' OR LAST_NAME LIKE '%'||:lastName||'%'", nativeQuery = true)
    List<Actors> findActorsByActorName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "SELECT * FROM DSS_ACTORS WHERE ACTOR_ID = :actorId", nativeQuery = true)
    List<Actors> findActorByActorId(@Param("actorId") String actorId);

    @Query(value = "SELECT MAX(ACTOR_ID) FROM DSS_ACTORS", nativeQuery = true)
    String maxActorId();
}
