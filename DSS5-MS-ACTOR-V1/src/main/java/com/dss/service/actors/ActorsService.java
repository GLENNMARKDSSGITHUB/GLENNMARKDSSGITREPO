/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.actors;

import com.dss.dto.actors.ActorsDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * This is a service layer for DSS Actors
 */

public interface ActorsService {

    /** Returns a String value if the admin user successfully adds the movie actor or not.
     * @param actorDto actorDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #addActor(ActorsDTO)
     */
    DssCommonMessageDetails addActor(ActorsDTO actorDto);

    /** Returns a list of Actors
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #displayActors()
     */
    DssCommonMessageDetails displayActors();

    /** Returns a specific movie actor record the form of a List <Actors>
     * @param firstName User's first name
     * @param lastName User's last name
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #searchActorByActorName(String, String)
     */
    DssCommonMessageDetails searchActorByActorName(String firstName, String lastName);

    /** Returns a String value if the admin user successfully updates the movie actor or not.
     * @param actorDto actorDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #updateActor(ActorsDTO)
     */
    DssCommonMessageDetails updateActor(ActorsDTO actorDto);

    /** Returns a String value if the admin user successfully deletes the movie actor or not.
     * @param firstName User's first name
     * @param lastName User's last name
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #deleteActor(String, String)
     */
    DssCommonMessageDetails deleteActor(String firstName, String lastName);
}
