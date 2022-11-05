package com.dss.client.service.actors;

import com.dss.client.dto.actors.ActorsDTO;

import java.util.List;

public interface ActorsService {

    String addActor(ActorsDTO actorsDto);

    List<ActorsDTO> displayActors();

    List<ActorsDTO> searchActorByActorName(String firstName, String lastName);

    String updateActor(ActorsDTO actorsDto);

    String deleteActor(String firstName, String lastName);
}
