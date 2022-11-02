/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.service.movie.DssMovieService;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonUtility;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /** Returns a ResponseEntity<String> value if the admin user successfully adds the movie or not.
     * @param movieDto movieDto
     * @return ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK)
     * @see #addDigiStreamMovie(DssMovieDTO)
     */
    @PostMapping("/add-digistreammovie.do")
    public ResponseEntity<String> addDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
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
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    /** Returns a list of ResponseEntity<List<DssMovie>>
     * @return ResponseEntity<>(actorList, HttpStatus.OK)
     * @see #displayDigiStreamMovie()
     */
    @GetMapping("/display-digistreammovie.do")
    public ResponseEntity<List<DssMovie>> displayDigiStreamMovie(){
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
        return new ResponseEntity<>(dssMovies, HttpStatus.OK);
    }

    /** Returns a specific movie in a form of ResponseEntityResponseEntity<List<DssMovie>>
     * @return ResponseEntity<>(actorList, HttpStatus.OK)
     * @see #searchDigiStreamMovie(HttpServletRequest)
     */
    @GetMapping("/search-digistreammovie.do")
    public ResponseEntity<List<DssMovie>> searchDigiStreamMovie(HttpServletRequest request){
        log.debug("DssMovieController | searchDigiStreamMovie | End");
        List<DssMovie> dssMovies = null;
        try{
            String movieTitle = request.getParameter("movieTitle");
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
        return new ResponseEntity<>(dssMovies, HttpStatus.OK);
    }

    /** Returns a ResponseEntity<String> value if the admin user successfully updates the movie or not.
     * @param movieDto movieDto
     * @return ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK)
     * @see #updateDigiStreamMovie(DssMovieDTO)
     */
    @PutMapping("/update-digistreammovie.do")
    public ResponseEntity<String> updateDigiStreamMovie(@RequestBody DssMovieDTO movieDto){
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
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    /** Returns a ResponseEntity<String> value if the admin user successfully deletes the movie or not.
     * @param request HttpServletRequest
     * @return ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK)
     * @see #deleteDigiStreamMovie(HttpServletRequest)
     */
    @DeleteMapping("/delete-digistreammovie.do")
    public ResponseEntity<String> deleteDigiStreamMovie(HttpServletRequest request){
        log.debug("DssMovieController | deleteDigiStreamMovie | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        String movieTitle = request.getParameter("movieTitle");
        try{
            commonMsgDtl = dssMovieService.deleteDssMovie(movieTitle);
            log.debug(commonMsgDtl.getContent());
        }catch (Exception ex){
            log.error("DssMovieController | deleteDigiStreamMovie | Error msg : " + ex.getMessage());
        }finally{
            log.debug("DssMovieController | deleteDigiStreamMovie | End");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

    /** Returns a ResponseEntity<String> value if the admin user successfully uploads the image or not.
     * @param request HttpServletRequest
     *
     * @return ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK)
     * @see #deleteDigiStreamMovie(HttpServletRequest)
     */
    @PostMapping("/upload-image.do")
    public ResponseEntity<String> uplaodImage(HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile){
        log.debug("DssMovieController | uplaodImage | Start");
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        try{
            String movieTitle = request.getParameter("movieTitle");
            commonMsgDtl = dssMovieService.uploadImage(movieTitle, multipartFile);
        }catch(Exception ex){
            log.error("DssMovieController| uploadImage | Error msg : " + ex.getMessage());
        }finally {
            log.debug("DssMovieController | uploadImage | End ");
        }
        return new ResponseEntity<>(commonMsgDtl.getContent(), HttpStatus.OK);
    }

}
