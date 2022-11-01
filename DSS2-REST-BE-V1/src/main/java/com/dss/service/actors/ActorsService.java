package com.dss.service.actors;

import com.dss.dto.actors.ActorsDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public interface ActorsService {

    DssCommonMessageDetails addActor(ActorsDTO actorDto);
    DssCommonMessageDetails displayActors();
    DssCommonMessageDetails searchActorByActorName(String firstName, String lastName);
    DssCommonMessageDetails updateActor(ActorsDTO actorDto);
    DssCommonMessageDetails deleteActor(String firstName, String lastName);
}
