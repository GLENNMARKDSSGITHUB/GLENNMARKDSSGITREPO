package com.dss.client.service.movie;

import com.dss.client.dto.movie.DssMovieDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface MovieService {

    String addDigiStreamMovie(@RequestBody DssMovieDTO movieDto);

    List<DssMovieDTO> displayDigiStreamMovie();

    List<DssMovieDTO> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    String updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto);

    String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
