/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.service.DssMovieService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller layer for DSS Movies
 */

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/API/movie-catalogue")
@Slf4j
public class DssMovieController {

    private final DssCommonUtility commonUtil = new DssCommonUtility();

    @Autowired
    private DssMovieService dssMovieService;

    /** Returns a String value if the admin user successfully adds the movie or not.
     * @param movieDto movieDto
     * @return String
     * @see #addDigiStreamMovie(DssMovieDTO)
     */
    @PostMapping("/add-digistreammovie.do")
    public String addDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        log.debug("DssMovieController | addDigiStreamMovie | Start");
        log.debug("DssMovieController | addDigiStreamMovie | userDTO: " + movieDto.toString());
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.addDssMovie(movieDto);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DssMovieController | addDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DssMovieController | addDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

    /** Returns a list of DssMovies
     * @return List<DssMovie>
     * @see #displayDigiStreamMovie()
     */
    @GetMapping("/display-digistreammovie.do")
    public List<DssMovie> displayDigiStreamMovie(){
        log.debug("DssMovieController | displayDigiStreamMovie | Start");
        List<DssMovie> dssMovies = null;
        try{
            DssCommonMessageDetails commonMsgDtl = dssMovieService.displayDssMovies();
            if(commonMsgDtl.isSuccess()){
                dssMovies = (List<DssMovie>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(dssMovies));
                log.debug("DssMovieController | displayDigiStreamMovie | movies : \n" + commonUtil.gsonToJsonString(dssMovies));
            }else{
                log.error("DssMovieController | displayDigiStreamMovie | movies : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("DssMovieController | displayDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DssMovieController | displayDigiStreamMovie | End");
        }
        return dssMovies;
    }

    /** Returns a specific movie in a form of List<DssMovie>
     * @param movieTitle movieTitle
     * @return List<DssMovie>
     * @see #searchDigiStreamMovie(String)
     */
    @GetMapping("/search-digistreammovie.do/{movieTitle}")
    public List<DssMovie> searchDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        log.debug("DssMovieController | searchDigiStreamMovie | End");
        List<DssMovie> dssMovies = null;
        try{
            DssCommonMessageDetails commonMsgDtl = dssMovieService.searchDssMovieByMovieTitle(movieTitle);
            if(commonMsgDtl.isSuccess()){
                dssMovies = (List<DssMovie>) commonMsgDtl.getObjList();
                commonMsgDtl.setContent(commonUtil.gsonToJsonString(dssMovies));
                log.debug("DssMovieController | searchDigiStreamMovie | movie : \n" + commonUtil.gsonToJsonString(dssMovies));
            }else{
                log.error("DssMovieController | searchDigiStreamMovie | movie : " + commonMsgDtl.getContent());
            }
        }catch (Exception ex){
            log.error("DssMovieController | searchDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DssMovieController | searchDigiStreamMovie | End");
        }
        return dssMovies;
    }

    /** Returns a String value if the admin user successfully updates the movie or not.
     * @param movieDto movieDto
     * @return String
     * @see #updateDigiStreamMovie(DssMovieDTO)
     */
    @PutMapping("/update-digistreammovie.do")
    public String updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
        log.debug("DssMovieController | updateDigiStreamMovie | End");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.updateDssMovie(movieDto);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DssMovieController | updateDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DssMovieController | updateDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }

    /** Returns a String value if the admin user successfully deletes the movie or not.
     * @param movieTitle HttpServletRequest
     * @return String
     * @see #deleteDigiStreamMovie(String)
     */
    @DeleteMapping("/delete-digistreammovie.do/{movieTitle}")
    public String deleteDigiStreamMovie(@PathVariable("movieTitle") String movieTitle){
        log.debug("DssMovieController | deleteDigiStreamMovie | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            commonMsgDtl = dssMovieService.deleteDssMovie(movieTitle);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DssMovieController | deleteDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DssMovieController | deleteDigiStreamMovie | End");
        }
        return commonMsgDtl.getContent();
    }
}
