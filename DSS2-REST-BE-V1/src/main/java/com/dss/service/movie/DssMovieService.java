/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * This is a service layer for DSS Movie
 */

public interface DssMovieService {

    /** Returns a String value if the admin user successfully adds the movie or not.
     * @param dssDto dssDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #addDssMovie(DssMovieDTO)
     */
    DssCommonMessageDetails addDssMovie(DssMovieDTO dssDto);

    /** Returns a list of Movies
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #displayDssMovies()
     */
    DssCommonMessageDetails displayDssMovies();

    /** Returns a specific movie record the form of a List <Movie>
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #searchDssMovieByMovieTitle(String)
     */
    DssCommonMessageDetails searchDssMovieByMovieTitle(String movieTitle);

    /** Returns a String value if the admin user successfully updates the movie or not.
     * @param dssDto dssDto
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #updateDssMovie(DssMovieDTO)
     */
    DssCommonMessageDetails updateDssMovie(DssMovieDTO dssDto);

    /** Returns a String value if the admin user successfully deletes the movie or not.
     * @param movieTitle movie title
     * @return DssCommonMessageDetails commonMsgDtl
     * @see #deleteDssMovie(String)
     */
    DssCommonMessageDetails deleteDssMovie(String movieTitle);
}
