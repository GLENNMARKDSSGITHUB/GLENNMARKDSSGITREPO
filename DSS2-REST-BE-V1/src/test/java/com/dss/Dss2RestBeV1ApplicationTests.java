package com.dss;

import com.dss.repository.movie.DssMovieRepository;
import com.dss.service.movie.DssMovieService;
import com.dss.util.utils.DssCommonUtility;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Dss2RestBeV1ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(Dss2RestBeV1ApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Autowired
    private DssMovieService dssMovieService;

    @MockBean
    private DssMovieRepository dssMovieRepository;

    private final DssCommonUtility commonUtil = new DssCommonUtility();

//    @Test
//    public void displayMovies(){
//        List<DssMovie> movieList = dssMovieRepository.findAll();
//        if(!movieList.isEmpty()){
//            logger.debug(commonUtil.gsonToJsonString(dssMovieService.populateDigiStreamMovie(movieList)));
//            assertEquals(1, movieList.size());
//            logger.debug("DigiStreamMovieServiceImpl | displayMovies | MovieList size : " + movieList.size());
//        }
//    }
//
//    @Test
//    public void searchMovieByMovieTitle(){
//        List<DssMovie> movieList = dssMovieRepository.findDigiStreamMovieByMovieTitle("The Avengers");
//        if(!movieList.isEmpty()){
//            logger.debug(commonUtil.gsonToJsonString(dssMovieService.populateDigiStreamMovie(movieList)));
//            assertEquals(1, movieList.size());
//        }
//    }



}
