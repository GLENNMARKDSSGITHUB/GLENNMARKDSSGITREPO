package com.dss.service.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.transformer.movie.DssMovieTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.dss.util.utils.DssCommonMethods;
import com.dss.util.enums.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DssMovieServiceImpl implements DssMovieService {
    private static final Logger logger = LoggerFactory.getLogger(DssMovieServiceImpl.class);
    private final DssMovieTransformer transformer = new DssMovieTransformer();
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
    private final DssCommonMethods commonMethods = new DssCommonMethods();

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Override
    public DssCommonMessageDetails addDssMovie(DssMovieDTO dssDto){
        logger.debug("DssMovieServiceImpl | addDssMovie | Start ");
        try{
            List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(dssDto.getMovieTitle());
            if(!movieList.isEmpty()){
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_CODE_001_MOVIE_EXIST, dssDto.getMovieTitle()));
                commonMsgDtl.setSuccess(false);
            }else{
                dssDto.setMovieId(commonMethods.dssIdGeneration(dssMovieRepository.maxDssId()));
                dssMovieRepository.save(transformer.transformToDssMovie(dssDto));
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
                commonMsgDtl.setObjList(transformer.transformToDssMovie((movieList)));
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
                commonMsgDtl.setObjList(transformer.transformToDssMovie((movieList)));
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
                dssDto.setLastModificationDate(new Date());
                dssDto.setLastModifiedBy(UserRoles.ROLE_ADMIN.toString());
                dssMovieRepository.save(transformer.transformToDssMovie(dssDto));
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
                actorsRepository.deleteAll(movieList.get(0).getMovieActors());
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
}
