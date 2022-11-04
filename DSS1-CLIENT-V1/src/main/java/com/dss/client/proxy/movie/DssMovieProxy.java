package com.dss.client.proxy.movie;

import com.dss.client.dto.movie.DssMovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${feign.movie-name}" , url ="${feign.movie-url}")
public interface DssMovieProxy {

    @PostMapping("/API/movie-catalogue/add-digistreammovie.do")
    String addDigiStreamMovie(@RequestBody DssMovieDTO movieDto);

    @GetMapping("/API/movie-catalogue/display-digistreammovie.do")
    List<DssMovieDTO> displayDigiStreamMovie();

    @GetMapping("/API/movie-catalogue/search-digistreammovie.do/{movieTitle}")
    List<DssMovieDTO> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);

    @PutMapping("/API/movie-catalogue/update-digistreammovie.do")
    String updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto);

    @DeleteMapping("/API/movie-catalogue/delete-digistreammovie.do/{movieTitle}")
    String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle);
}
