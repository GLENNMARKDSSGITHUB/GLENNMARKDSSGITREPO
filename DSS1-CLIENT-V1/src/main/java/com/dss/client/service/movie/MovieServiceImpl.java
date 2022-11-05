package com.dss.client.service.movie;

import com.dss.client.dto.movie.DssMovieDTO;
import com.dss.client.entity.movie.DssMovie;
import com.dss.client.proxy.movie.DssMovieProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private DssMovieProxy dssMovieProxy;

    @Override
    public String addDigiStreamMovie(DssMovieDTO movieDto) {
        return dssMovieProxy.addDigiStreamMovie(movieDto);
    }

    @Override
    public List<DssMovie> displayDigiStreamMovie() {
        return dssMovieProxy.displayDigiStreamMovie();
    }

    @Override
    public List<DssMovie> searchDigiStreamMovie(String movieTitle) {
        return dssMovieProxy.searchDigiStreamMovie(movieTitle);
    }

    @Override
    public String updateDigiStreamMovie(DssMovieDTO movieDto) {
        return dssMovieProxy.updateDigiStreamMovie(movieDto);
    }

    @Override
    public String deleteDigiStreamMovie(String movieTitle) {
        return dssMovieProxy.deleteDigiStreamMovie(movieTitle);
    }
}
