/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.image.Images;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.image.FileStorageRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.transformer.image.ImageTransformer;
import com.dss.transformer.movie.DssMovieTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class is a service implementation for DSS Movie
 * @see #addDssMovie(DssMovieDTO)
 * @see #displayDssMovies()
 * @see #searchDssMovieByMovieTitle(String)
 * @see #updateDssMovie(DssMovieDTO)
 * @see #deleteDssMovie(String)
 * @see #uploadImage(String, MultipartFile)
 */

@Service
public class DssMovieServiceImpl implements DssMovieService {
    private static final Logger logger = LoggerFactory.getLogger(DssMovieServiceImpl.class);
    private final DssMovieTransformer transformer = new DssMovieTransformer();
    private final ImageTransformer imageTransformer = new ImageTransformer();
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final DssCommonMethods commonMethods = new DssCommonMethods();

    @Value("${dss-file-path}")
    private String pathName;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private FileStorageRepository fileStorage;

    @Override
    public DssCommonMessageDetails addDssMovie(DssMovieDTO dssDto){
        logger.debug("DssMovieServiceImpl | addDssMovie | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(dssDto.getMovieTitle());
            if(!movieList.isEmpty()){
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_001_MOVIE_EXIST, dssDto.getMovieTitle()));
                commonMsgDtl.setSuccess(false);
            }else{
                String movieId = commonMethods.dssIdGeneration(dssMovieRepository.maxMovieId());
                dssMovieRepository.save(transformer.transformToDssMovieAdd(dssDto, movieId));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_001_ADD_MOV, dssDto.getMovieId()));
                commonMsgDtl.setSuccess(true);
            }
        }catch(Exception ex){
            logger.error("DssMovieServiceImpl | addDssMovie | Error msg : " + ex.getMessage());
        }finally{
            logger.debug("DssMovieServiceImpl | addDssMovie | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayDssMovies(){
        logger.debug("DssMovieServiceImpl | displayDssMovies | Start ");
        try{    
            List<DssMovie> movieList = dssMovieRepository.findAll();
            if(!movieList.isEmpty()){
                List<DssMovie> movies = transformer.transformToDssMovie((movieList));
                commonMsgDtl.setObjList(movies);
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("DssMovieServiceImpl | displayDssMovies | Error msg : " + ex.getMessage());
        }finally {
            logger.debug("DssMovieServiceImpl | displayDssMovies | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails searchDssMovieByMovieTitle(String movieTitle) {
        logger.debug("DssMovieServiceImpl | searchDssMovieByMovieTitle | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(movieTitle);
            if(!movieList.isEmpty()){
                List<DssMovie> movies = transformer.transformToDssMovie((movieList));
                commonMsgDtl.setObjList(movies);
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("DssMovieServiceImpl | searchDssMovieByMovieTitle | Error msg : " + ex.getMessage());
        }finally {
            logger.debug("DssMovieServiceImpl | searchDssMovieByMovieTitle | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails updateDssMovie(DssMovieDTO dssDto) {
        logger.debug("DssMovieServiceImpl | updateDssMovie | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(dssDto.getMovieId());
            if(!movieList.isEmpty()){
                dssMovieRepository.save(transformer.transformToDssMovieUpdate(dssDto));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_002_UPDATE_MOV, dssDto.getMovieTitle()));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("DssMovieServiceImpl | updateDssMovie | Error msg : " + ex.getMessage());
        }finally {
            logger.debug("DssMovieServiceImpl | updateDssMovie | End");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails deleteDssMovie(String movieTitle) {
        logger.debug("DssMovieServiceImpl | deleteDssMovie | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(movieTitle);
            if(!movieList.isEmpty()){
                List<Reviews> reviewsList = movieList.get(0).getMovieReviews();
                reviewsRepository.deleteAll(reviewsList);

                List<Actors> actorsList = movieList.get(0).getMovieActors();
                actorsRepository.deleteAll(actorsList);

                List<Images> imagesList = movieList.get(0).getImage();
                fileStorage.deleteAll(imagesList);

                dssMovieRepository.delete(movieList.get(0));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_CODE_003_DELETE_MOV, movieTitle));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
                commonMsgDtl.setSuccess(false);
            }
        }catch(Exception ex){
            logger.error("DssMovieServiceImpl | deleteDssMovie | Error msg : " + ex.getMessage());
        }finally {
            logger.debug("DssMovieServiceImpl | deleteDssMovie | End ");
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails uploadImage(String movieTitle, MultipartFile multipartFile){
        logger.debug("DssMovieServiceImpl | uplaodImage | Start ");
        try{
            File f = new File(pathName);
            if(!f.mkdir()){
                logger.error("DssMovieServiceImpl | uploadImage | Folder not created.");
            }

            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(movieTitle);
            if(!movieList.isEmpty()){
                String filePath = pathName + multipartFile.getOriginalFilename();
                logger.debug("DssMovieServiceImpl | uploadImage | file path : " + filePath);
                multipartFile.transferTo(new File(filePath));
                logger.debug("DssMovieServiceImpl | uploadImage | Transfer file successfully!");

                Images image = imageTransformer.transformToImage(multipartFile, movieList.get(0), filePath);
                fileStorage.save(image);
                commonMsgDtl.setContent("Image uploaded successfully!");
            }else{
                commonMsgDtl.setContent(CommonStringUtility.ERR_CODE_002_NO_DISPLAY_RECORDS);
            }
        }catch(IOException ex){
            logger.error("DssMovieServiceImpl | uploadImage | Error msg : " + ex.getMessage());
        }finally {
            logger.debug("DssMovieServiceImpl | uploadImage | End ");
        }
        return  commonMsgDtl;
    }

}
