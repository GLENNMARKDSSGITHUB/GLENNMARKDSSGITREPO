package com.dss.controller.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.service.movie.DssMovieService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/movie-catalogue")
public class DssMovieController {
    private static final Logger logger = LoggerFactory.getLogger(DssMovieController.class);

    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private DssMovieService dssMovieService;

    @PostMapping("/add-digistreammovie.do")
    public String addDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        logger.debug("DigiStreamMovieController | addDigiStreamMovie | Start");
        logger.debug("DigiStreamMovieController | addDigiStreamMovie | userDTO: " + movieDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.addDssMovie(movieDto);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("DigiStreamMovieController | addDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("DigiStreamMovieController | addDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/display-digistreammovie.do")
    public String displayDigiStreamMovie(){
        logger.debug("DigiStreamMovieController | displayDigiStreamMovie | Start");
        DssCommonMessageDetails commonMsgDtl = dssMovieService.displayDssMovies();
        try{
            if(commonMsgDtl.isSuccess()){
                List<DssMovie> dssMovies = (List<DssMovie>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(dssMovies));
                logger.debug("DigiStreamMovieController | displayDigiStreamMovie | movies : \n" + commonUtil.gsonToJsonString(dssMovies));
            }else{
                logger.error("DigiStreamMovieController | displayDigiStreamMovie | movies : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("DigiStreamMovieController | displayDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("DigiStreamMovieController | displayDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

    @GetMapping("/search-digistreammovie.do")
    public String searchDigiStreamMovie(HttpServletRequest request){
        logger.debug("DigiStreamMovieController | searchDigiStreamMovie | End");
        String movieTitle = request.getParameter("movieTitle");
        DssCommonMessageDetails commonMsgDtl = dssMovieService.searchDssMovieByMovieTitle(movieTitle);
        try{
            if(commonMsgDtl.isSuccess()){
                List<DssMovie> dssMovies = (List<DssMovie>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(dssMovies));
                logger.debug("DigiStreamMovieController | searchDigiStreamMovie | movie : \n" + commonUtil.gsonToJsonString(dssMovies));
            }else{
                logger.error("DigiStreamMovieController | searchDigiStreamMovie | movie : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            logger.error("DigiStreamMovieController | searchDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("DigiStreamMovieController | searchDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

    @PutMapping("/update-digistreammovie.do")
    public String updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        logger.debug("DigiStreamMovieController | updateDigiStreamMovie | End");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.updateDssMovie(movieDto);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("DigiStreamMovieController | updateDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("DigiStreamMovieController | updateDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

    @DeleteMapping("/delete-digistreammovie.do")
    public String deleteDigiStreamMovie(HttpServletRequest request){
        logger.debug("DigiStreamMovieController | deleteDigiStreamMovie | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        String movieTitle = request.getParameter("movieTitle");
        try{
            commonMsgDtl = dssMovieService.deleteDssMovie(movieTitle);
            logger.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            logger.error("DigiStreamMovieController | deleteDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("DigiStreamMovieController | deleteDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

}
