package com.dss.service.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.util.utils.DssCommonMessageDetails;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

public interface DssMovieService {

    DssCommonMessageDetails addDssMovie(DssMovieDTO dssDto);
    DssCommonMessageDetails displayDssMovies();
    DssCommonMessageDetails searchDssMovieByMovieTitle(String movieTitle);
    DssCommonMessageDetails updateDssMovie(DssMovieDTO dssDto);
    DssCommonMessageDetails deleteDssMovie(String movieTitle);
}
