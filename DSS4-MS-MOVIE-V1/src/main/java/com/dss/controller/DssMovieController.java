package com.dss.controller;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.service.movie.DssMovieService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/movie-catalogue")
@Slf4j
public class DssMovieController {

    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private DssMovieService dssMovieService;

    @PostMapping("/add-digistreammovie.do")
    public ResponseEntity<String> addDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        log.debug("DigiStreamMovieController | addDigiStreamMovie | Start");
        log.debug("DigiStreamMovieController | addDigiStreamMovie | userDTO: " + movieDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.addDssMovie(movieDto);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DigiStreamMovieController | addDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DigiStreamMovieController | addDigiStreamMovie | End");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    @GetMapping("/display-digistreammovie.do")
    public ResponseEntity<List<DssMovie>> displayDigiStreamMovie(){
        log.debug("DigiStreamMovieController | displayDigiStreamMovie | Start");
        List<DssMovie> dssMovies = null;
        try{
            DssCommonMessageDetails commonMsgDtl = dssMovieService.displayDssMovies();
            if(commonMsgDtl.isSuccess()){
                dssMovies = (List<DssMovie>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(dssMovies));
                log.debug("DigiStreamMovieController | displayDigiStreamMovie | movies : \n" + commonUtil.gsonToJsonString(dssMovies));
            }else{
                log.error("DigiStreamMovieController | displayDigiStreamMovie | movies : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("DigiStreamMovieController | displayDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DigiStreamMovieController | displayDigiStreamMovie | End");
        }
        return new ResponseEntity<>(dssMovies, HttpStatus.OK);
    }

    @GetMapping("/search-digistreammovie.do")
    public ResponseEntity<List<DssMovie>> searchDigiStreamMovie(HttpServletRequest request){
        log.debug("DigiStreamMovieController | searchDigiStreamMovie | End");
        List<DssMovie> dssMovies = null;
        try{
            String movieTitle = request.getParameter("movieTitle");
            DssCommonMessageDetails commonMsgDtl = dssMovieService.searchDssMovieByMovieTitle(movieTitle);
            if(commonMsgDtl.isSuccess()){
                dssMovies = (List<DssMovie>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(dssMovies));
                log.debug("DigiStreamMovieController | searchDigiStreamMovie | movie : \n" + commonUtil.gsonToJsonString(dssMovies));
            }else{
                log.error("DigiStreamMovieController | searchDigiStreamMovie | movie : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("DigiStreamMovieController | searchDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DigiStreamMovieController | searchDigiStreamMovie | End");
        }
        return new ResponseEntity<>(dssMovies, HttpStatus.OK);
    }

    @PutMapping("/update-digistreammovie.do")
    public ResponseEntity<String> updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        log.debug("DigiStreamMovieController | updateDigiStreamMovie | End");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.updateDssMovie(movieDto);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DigiStreamMovieController | updateDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DigiStreamMovieController | updateDigiStreamMovie | End");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-digistreammovie.do")
    public ResponseEntity<String> deleteDigiStreamMovie(HttpServletRequest request){
        log.debug("DigiStreamMovieController | deleteDigiStreamMovie | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        String movieTitle = request.getParameter("movieTitle");
        try{
            commonMsgDtl = dssMovieService.deleteDssMovie(movieTitle);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DigiStreamMovieController | deleteDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DigiStreamMovieController | deleteDigiStreamMovie | End");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

}
