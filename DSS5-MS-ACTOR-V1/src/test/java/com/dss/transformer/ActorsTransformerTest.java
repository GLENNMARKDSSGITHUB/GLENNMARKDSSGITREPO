package com.dss.transformer;

import com.dss.dto.actors.ActorsDTO;
import com.dss.entity.actors.Actors;

public class ActorsTransformerTest {

    public Actors transformToActor(ActorsDTO actorDto){
        return new Actors(
                actorDto.getActorId(),
                actorDto.getMovieId(),
                actorDto.getFirstName(),
                actorDto.getLastName(),
                actorDto.getGender(),
                actorDto.getAge(),
                actorDto.getMovieCast(),
                actorDto.getRole(),
                actorDto.getCreationDate(),
                actorDto.getCreatedBy(),
                actorDto.getLastModificationDate(),
                actorDto.getLastModifiedBy()
        );
    }
}
