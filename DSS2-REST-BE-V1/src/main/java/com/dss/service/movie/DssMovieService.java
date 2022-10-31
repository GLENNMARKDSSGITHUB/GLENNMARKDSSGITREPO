package com.dss.service.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.util.utils.DssCommonMessageDetails;

public interface DssMovieService {

    DssCommonMessageDetails addDssMovie(DssMovieDTO dssDto);
    DssCommonMessageDetails displayDssMovies();
    DssCommonMessageDetails searchDssMovieByMovieTitle(String movieTitle);
    DssCommonMessageDetails updateDssMovie(DssMovieDTO dssDto);
    DssCommonMessageDetails deleteDssMovie(String movieTitle);
}
