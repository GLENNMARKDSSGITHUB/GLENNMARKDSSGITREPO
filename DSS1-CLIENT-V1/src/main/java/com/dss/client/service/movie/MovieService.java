package com.dss.client.service.movie;

import com.dss.client.dto.movie.DssMovieDTO;
import com.dss.client.entity.movie.DssMovie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieService {

    String addDigiStreamMovie(@RequestBody DssMovieDTO movieDto);

    List<DssMovie> displayDigiStreamMovie();

    List<DssMovie> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    String updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto);

    String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
