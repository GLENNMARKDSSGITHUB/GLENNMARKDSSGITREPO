package com.dss.client.controller.movie;

import com.dss.client.dto.movie.DssMovieDTO;
import com.dss.client.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/movie-catalogue")
public class DssMovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add-digistreammovie.do")
    public String addDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        return movieService.addDigiStreamMovie(movieDto);
    }

    @GetMapping("/display-digistreammovie.do")
    public List<DssMovieDTO> displayDigiStreamMovie(){
        return movieService.displayDigiStreamMovie();
    }

    @GetMapping("search-digistreammovie.do/{movieTitle}")
    public List<DssMovieDTO> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        return movieService.searchDigiStreamMovie(movieTitle);
    }

    @PutMapping("/update-digistreammovie.do")
    public String updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        return movieService.updateDigiStreamMovie(movieDto);
    }

    @DeleteMapping("/delete-digistreammovie.do/{movieTitle}")
    public String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        return movieService.deleteDigiStreamMovie(movieTitle);
    }
}
