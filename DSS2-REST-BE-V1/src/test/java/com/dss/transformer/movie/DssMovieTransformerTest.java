package com.dss.transformer.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;


public class DssMovieTransformerTest {

    public DssMovie dssMovie(DssMovieDTO dssDto){
        return new DssMovie(
                dssDto.getMovieId(),
                dssDto.getMovieTitle(),
                dssDto.getYear(),
                dssDto.getWriters(),
                dssDto.getDirectedBy(),
                dssDto.getProducedBy(),
                dssDto.getMusicBy(),
                dssDto.getDuration(),
                dssDto.getMovieCost(),
                dssDto.getCategory(),
                dssDto.getCountry(),
                dssDto.getLanguage(),
                dssDto.getCreationDate(),
                dssDto.getCreatedBy(),
                dssDto.getLastModificationDate(),
                dssDto.getLastModifiedBy()
        );
    }

}
